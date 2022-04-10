package com.suszkolabs.controller;

import com.suszkolabs.entity.Ticket;
import com.suszkolabs.entity.Unit;
import com.suszkolabs.service.TicketService;
import com.suszkolabs.service.UnitService;
import com.suszkolabs.utils.IdCheckboxWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    //TODO make a limit as an user-defined option
    //TODO add checkboxes responsible for completion/incompletion of certain tickets

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UnitService unitService;

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
        model.addAttribute("activeTickets", ticketService.getTicketsByCompletion(false));
        model.addAttribute("collectedIds", new IdCheckboxWrapper());

        return "active-tickets";
    }

    /*
    @PostMapping("/active")
    public String activeTicketsProcess(@ModelAttribute("collectedIds") List<Ticket> collectedIds){
        System.out.println(collectedIds);
        return "redirect:/tickets/active";
    }
     */


    @GetMapping("/completed")
    public String completedTickets(Model model){
        model.addAttribute("completedTickets", ticketService.getTicketsByCompletion(true));
        return "completed-tickets";
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
