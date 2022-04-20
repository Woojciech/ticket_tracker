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
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    //TODO make a limit as an user-defined option
    //TODO add checkboxes responsible for completion/incompletion of certain completedTickets

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UnitService unitService;

    private static final int PAGE_SIZE = 10;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmer);
    }

    @GetMapping("/test")
    public String designTest(){
        return "design_prototype/prototype";
    }

    @GetMapping("/test2")
    public String designTest2(Model model){
        model.addAttribute("ticket", new Ticket());
        return "design_prototype/prototype-form";
    }

    @GetMapping ("/dashboard")
    public String showDashboard(Model model){
        model.addAttribute("dashboardTickets", ticketService.getLimitedTicketsByCompletion(3));
        return "dashboard-tickets";
    }

    @GetMapping("/active")
    public String activeTickets(Model model, HttpServletRequest request){
        //setReferer(request);
        prepareGeneralPagination(1, model, false);
        return "active-tickets";
    }

    @GetMapping("/active/{pageNumber}")
    public String activeTicketsPage(@PathVariable(value="pageNumber") int pageNumber, Model model, HttpServletRequest request){
        prepareGeneralPagination(pageNumber, model, false);
        return "active-tickets";
    }

    @GetMapping("/completed")
    public String completedTickets(Model model){
        prepareGeneralPagination(1, model, true);
        return "completed-tickets";
    }

    @GetMapping("/completed/{pageNumber}")
    public String completedTicketsPage(@PathVariable(value="pageNumber") int pageNumber, Model model){

        prepareGeneralPagination(pageNumber, model, true);

        return "completed-tickets";
    }

    private void prepareGeneralPagination(int pageNumber, Model model, boolean completionStatus){
        Long ticketsCount = ticketService.countTicketsByCompletion(completionStatus);
        long pageCount = 0;
        if(ticketsCount % (long) PAGE_SIZE == 0)
           pageCount = ticketsCount / PAGE_SIZE;
        else
            pageCount = (ticketsCount / PAGE_SIZE) + 1;


        if(completionStatus) {
            model.addAttribute("pageCountCompleted", pageCount);
            model.addAttribute("completedTickets", ticketService.getTicketsByCompletionPaginate(true, PAGE_SIZE, pageNumber - 1));
        }else {
            model.addAttribute("pageCountActive", pageCount);
            model.addAttribute("activeTickets", ticketService.getTicketsByCompletionPaginate(false, PAGE_SIZE, pageNumber - 1));
        }
    }

    private void prepareUnitPagination(int unitId, int pageNumber, Model model, boolean completionStatus){
        Long ticketsCount = ticketService.countUnitTicketsByCompletion(unitId, completionStatus);
        long pageCount = 0;
        if(ticketsCount % (long) PAGE_SIZE == 0)
            pageCount = ticketsCount / PAGE_SIZE;
        else
            pageCount = (ticketsCount / PAGE_SIZE) + 1;


        if(completionStatus) {
            model.addAttribute("pageCountCompleted", pageCount);
            model.addAttribute("completedTickets", ticketService.getUnitTicketsByCompletionPaginate(unitId,true, PAGE_SIZE, pageNumber - 1));
        }else {
            model.addAttribute("pageCountActive", pageCount);
            model.addAttribute("activeTickets", ticketService.getUnitTicketsByCompletionPaginate(unitId,false, PAGE_SIZE, pageNumber - 1));
        }
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
        return "ticket-form-add";
    }

    //TODO - to much logic in a controller?
    //TODO - mechanism does not work properly, units should be fetched differently
    @PostMapping("/add")
    public String addTicketProcess(Model model, @Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()) {
            // units are fetched on each error, it is by no means an optimal solution, is there a way to avoid it?
            model.addAttribute("units", unitService.getAllUnits());
            return "ticket-form-add";
        }
        ticket.setDateNow();

        // form binds actual unitId to new Unit object passed in "addTicket" (GET request)
        // then the id is used to fetch Unit from the database
        // ticket "relatedUnit" field is set to fetched Unit

        setRelatedTicketUnit(ticket);
        ticketService.saveTicket(ticket);

        return "redirect:" + previousUrlExtractor(request);
    }


    //TODO - switch findTicketById return type to optional
    @GetMapping("/update")
    public String updateTicketForm(@RequestParam("id") int ticketId, Model model, HttpServletRequest request){
        setReferer(request);

        Ticket updatedTicket = ticketService.findTicketById(ticketId);
        model.addAttribute("updatedTicket", updatedTicket);
        model.addAttribute("units", unitService.getAllUnits());

        request.getSession().setAttribute("previousUnitId", updatedTicket.getRelatedUnit().getId());
        return "ticket-form-update";
    }

    @PostMapping("/update")
    public String updateTicketProcess(Model model, @Valid @ModelAttribute("updatedTicket") Ticket updatedTicket, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            model.addAttribute("units", unitService.getAllUnits());
            return "ticket-form-update";
        }

        HttpSession session = request.getSession();

        if(updatedTicket.getRelatedUnit().getId() != (int) session.getAttribute("previousUnitId"))
            setRelatedTicketUnit(updatedTicket);

        ticketService.updateTicket(updatedTicket);

        session.removeAttribute("previousUnitId");

        return "redirect:" + previousUrlExtractor(request);
    }

    // form binds actual unitId to new Unit object passed in "addTicket" (GET request)
    // then the id is used to fetch Unit from the database
    // ticket "relatedUnit" field is set to fetched Unit
    private void setRelatedTicketUnit(Ticket ticket){
        int unitId = ticket.getRelatedUnit().getId();
        ticket.setRelatedUnit(unitService.findUnitById(unitId));
    }

    @GetMapping("/delete")
    public String deleteTicket(@RequestParam("id") int ticketId, HttpServletRequest request){
        setReferer(request);
        ticketService.deleteTicket(ticketId);
        return "redirect:" + previousUrlExtractor(request);
    }

    @GetMapping("/units/{pageNumber}")
    public String showUnits(@PathVariable(value = "pageNumber", required = false) Integer pageNumber, Model model, HttpServletRequest request){
        // saves paging details for further redirect
        request.getSession().setAttribute("referencePage", pageNumber);

        Long unitCount = unitService.getUnitsCount();
        long unitPageCount;
        //TODO repetitive method, extract it
        if(unitCount % PAGE_SIZE == 0)
            unitPageCount = unitCount / PAGE_SIZE;
        else
            unitPageCount = (unitCount / PAGE_SIZE) + 1;

        model.addAttribute("unitPageCount", unitPageCount);

        if(pageNumber == null)
            model.addAttribute("units", unitService.getUnitsPaginate(0, PAGE_SIZE));
        else
            model.addAttribute("units", unitService.getUnitsPaginate(pageNumber - 1, PAGE_SIZE));

        return "display-units";
    }

    @GetMapping(value = {"/units/unit"})
    public String showUnit(@RequestParam("id") int id, @RequestParam(value = "active", required = false) Integer activePage,
                           @RequestParam(value = "completed", required = false) Integer completedPage, Model model, HttpServletRequest request){
        model.addAttribute("unit", unitService.findUnitById(id));
        //setReferer(request);

        HttpSession session = request.getSession();
        //Integer previousActivePage = (Integer) session.getAttribute("previousActivePage");
        //Integer previousCompletedPage = (Integer) session.getAttribute("previousCompletedPage");

        // initial get request
        if(activePage == null && completedPage == null) {
            prepareUnitPagination(id,1, model, false);

            prepareUnitPagination(id,1, model, true);

            session.setAttribute("previousActivePage", 1);
            session.setAttribute("previousCompletedPage", 1);
        }else{
            prepareUnitPagination(id, activePage, model, false);
            prepareUnitPagination(id, completedPage, model, true);

            session.setAttribute("previousActivePage", activePage);
            session.setAttribute("previousCompletedPage", completedPage);
        }

        /*
        else if(activePage > previousActivePage)
            switchPage(id, model, session, true, true, previousActivePage, previousCompletedPage);
        else if(completedPage > previousCompletedPage)
            switchPage(id, model, session, false, true, previousActivePage, previousCompletedPage);
        else if(activePage < previousActivePage)
            switchPage(id, model, session, true, false, previousActivePage, previousCompletedPage);
        else if(completedPage < previousCompletedPage)
            switchPage(id, model, session, false, false, previousActivePage, previousCompletedPage);
        else{
            prepareUnitPagination(id, previousActivePage, model, false);
            prepareUnitPagination(id, previousCompletedPage, model, true);
        }
        */
        return "unit-details";
    }

    private void switchPage(int unitId, Model model, HttpSession session, boolean isActiveSwitched, boolean isForwardSwitch, Integer previousActivePage, Integer previousCompletedPage){
        int newPageNumber;
        if(isActiveSwitched){
            if(isForwardSwitch)
                newPageNumber = previousActivePage + 1;
            else
                newPageNumber = previousActivePage - 1;

            prepareUnitPagination(unitId, newPageNumber, model, false);
            session.setAttribute("previousActivePage", newPageNumber);

            prepareUnitPagination(unitId, previousCompletedPage, model, true);
        }else{
            if(isForwardSwitch)
                newPageNumber = previousCompletedPage + 1;
            else
                newPageNumber = previousCompletedPage - 1;

            prepareUnitPagination(unitId,newPageNumber, model, true);
            session.setAttribute("previousCompletedPage", newPageNumber);

            prepareUnitPagination(unitId, previousActivePage, model, false);
        }
    }

    @GetMapping("/units/add")
    public String addUnit(Model model, HttpServletRequest request){
        setReferer(request);

        model.addAttribute("unit", new Unit());
        return "unit-form-add";
    }

    @PostMapping("/units/add")
    public String addUnitProcess(@Valid @ModelAttribute("unit") Unit unit, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "unit-form-add";

        unitService.saveUnit(unit);
        return "redirect:/tickets/units/unit?id=" + unit.getId();
    }

    @GetMapping("/units/update")
    public String updateUnit(@RequestParam("id") int unitId, Model model, HttpServletRequest request){
        setReferer(request);
        Unit updatedUnit = unitService.findUnitById(unitId);
        model.addAttribute("updatedUnit", updatedUnit);

        return "unit-form-update";
    }

    @PostMapping("/units/update")
    public String updateUnitProcess(@Valid @ModelAttribute("updatedUnit") Unit updatedUnit, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors())
            return "unit-form-update";

        unitService.updateUnit(updatedUnit);
        return "redirect:" + previousUrlExtractor(request);
    }


    // session attribute which indicates page the request is coming from - used in later redirects
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
