package com.suszkolabs.controller;

import com.suszkolabs.entity.Ticket;
import com.suszkolabs.entity.Unit;
import com.suszkolabs.service.TicketService;
import com.suszkolabs.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    //TODO make a limit as an user-defined option
    //TODO add checkboxes responsible for completion/incompletion of certain tickets

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UnitService unitService;

    private static final int PAGE_SIZE = 20;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmer);
    }

    @GetMapping ("/dashboard")
    public String showDashboard(Model model){
        model.addAttribute("dashboardTickets", ticketService.getLimitedTicketsByCompletion(3));
        return "dashboard-tickets";
    }

    @GetMapping("/active")
    public String activeTickets(Model model){

        /*
        Long activeTicketsCount = ticketService.countTicketsByCompletion(false);
        model.addAttribute("pagesCount", activeTicketsCount / PAGE_SIZE);
        model.addAttribute("activeTickets", ticketService.getTicketsByCompletionPaginate(false, PAGE_SIZE, 0));
         */
        preparePagination(1, model, false);
        return "active-tickets";
    }

    @GetMapping("/active/{pageNumber}")
    public String activeTicketsPage(@PathVariable(value="pageNumber") int pageNumber, Model model){
        /*
        Long activeTicketsCount = ticketService.countTicketsByCompletion(false);
        model.addAttribute("pagesCount", activeTicketsCount / PAGE_SIZE);
        model.addAttribute("activeTickets", ticketService.getTicketsByCompletionPaginate(false, PAGE_SIZE, pageNumber - 1));
         */
        preparePagination(pageNumber, model, false);
        return "active-tickets";
    }

    @GetMapping("/completed")
    public String completedTickets(Model model){
        /*
        Long completedTicketsCount = ticketService.countTicketsByCompletion(true);
        model.addAttribute("pagesCount", completedTicketsCount / PAGE_SIZE);
        model.addAttribute("completedTickets", ticketService.getTicketsByCompletionPaginate(true, PAGE_SIZE, 0));
         */
        preparePagination(1, model, true);
        return "completed-tickets";
    }

    @GetMapping("/completed/{pageNumber}")
    public String completedTicketsPage(@PathVariable(value="pageNumber") int pageNumber, Model model){

        preparePagination(pageNumber, model, true);

        /*
        Long completedTicketsCount = ticketService.countTicketsByCompletion(true);
        model.addAttribute("pagesCount", completedTicketsCount / PAGE_SIZE);
        model.addAttribute("completedTickets", ticketService.getTicketsByCompletionPaginate(true, PAGE_SIZE, pageNumber - 1));
         */
        return "completed-tickets";
    }

    private void preparePagination(int pageNumber, Model model, boolean completionStatus){
        Long ticketsCount = ticketService.countTicketsByCompletion(completionStatus);
        long pageCount = 0;
        if(ticketsCount % (long) PAGE_SIZE == 0)
           pageCount = ticketsCount / PAGE_SIZE;
        else
            pageCount = (ticketsCount / PAGE_SIZE) + 1;

        model.addAttribute("pagesCount", pageCount);
        model.addAttribute("tickets", ticketService.getTicketsByCompletionPaginate(completionStatus, PAGE_SIZE, pageNumber - 1));
    }

    @GetMapping("/changeCompletionStatus")
    public String changeTicketStatus(@RequestParam("id") int ticketId, @RequestParam("currentStatus") boolean currentStatus, HttpServletRequest request){
        ticketService.changeCompletionStatus(ticketId, currentStatus);

        setReferer(request);
        return "redirect:" + previousUrlExtractor(request);
    }

    @GetMapping("/add")
    public String addTicket(Model model, HttpServletRequest request){

        // session attribute which indicates page the request is coming from - used in later redirects
        //request.getSession().setAttribute("referer", request.getHeader("Referer"));
        setReferer(request);

        // create ticket
        Ticket ticket = new Ticket();
        // set empty unit in order to map later within form
        ticket.setRelatedUnit(new Unit());

        // form object binding attributes
        model.addAttribute("ticket", ticket);
        model.addAttribute("units", unitService.getAllUnits());
        return "ticket-form";
    }

    //TODO - to much logic in a controller?
    //TODO - mechanism does not work properly, units should be fetched differently
    @PostMapping("/add")
    public String addTicketProcess(Model model, @Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()) {
            // units are fetched on each error, it is by no means an optimal solution, is there a way to avoid it?
            model.addAttribute("units", unitService.getAllUnits());
            return "ticket-form";
        }
        ticket.setDateNow();

        // form binds actual unitId to new Unit object passed in "addTicket" (GET request)
        // then the id is used to fetch the entire Unit from the database
        // ticket "relatedUnit" field is set to fetched Unit
        int unitId = ticket.getRelatedUnit().getId();
        ticket.setRelatedUnit(unitService.findUnitById(unitId));
        ticketService.saveTicket(ticket);

        /*
        String absoluteRedirectUrl = (String) request.getSession().getAttribute("ticketAddReferer");
        String trimmedRedirectUrl = absoluteRedirectUrl.split(request.getContextPath())[1];
        request.getSession().removeAttribute("ticketAddReferer");
         */

        return "redirect:" + previousUrlExtractor(request);
    }

    @GetMapping("/delete")
    public String deleteTicket(@RequestParam("id") int ticketId, HttpServletRequest request){
        setReferer(request);
        ticketService.deleteTicket(ticketId);
        return "redirect:" + previousUrlExtractor(request);
    }

    @GetMapping("/units")
    public String showUnits(Model model){
        model.addAttribute("units", unitService.getAllUnits());
        return "display-units";
    }

    @GetMapping("/units/unit")
    public String showUnit(@RequestParam(value = "id") int id, Model model){
        model.addAttribute("unit", unitService.findUnitByIdEager(id));
        return "unit-details";
    }

    @GetMapping("/units/add")
    public String addUnit(Model model){
        model.addAttribute("unit", new Unit());
        return "unit-form";
    }

    @PostMapping("/units/add")
    public String addUnitProcess(@Valid @ModelAttribute("unit") Unit unit, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "unit-form";

        unitService.saveUnit(unit);
        return "redirect:/tickets/units/unit?id=" + unit.getId();
    }

    private void setReferer(HttpServletRequest request){
        request.getSession().setAttribute("referer", request.getHeader("Referer"));
    }

    private String previousUrlExtractor(HttpServletRequest request){
        String absoluteRedirectUrl = (String) request.getSession().getAttribute("referer");
        String trimmedRedirectUrl = absoluteRedirectUrl.split(request.getContextPath())[1];
        request.getSession().removeAttribute("referer");

        return trimmedRedirectUrl;
    }
}
