package com.suszkolabs.controller;

import com.suszkolabs.entity.Ticket;
import com.suszkolabs.entity.Unit;
import com.suszkolabs.service.TicketService;
import com.suszkolabs.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    //TODO make a limit as an user-defined option

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UnitService unitService;

    @GetMapping ("/dashboard")
    public String showDashboard(Model model){
        model.addAttribute("dashboardTickets", ticketService.getLimitedTicketsByCompletion(3));
        return "dashboard";
    }

    @GetMapping("/active")
    public String activeTickets(Model model){
        model.addAttribute("activeTickets", ticketService.getTicketsByCompletion(false));
        return "active-tickets";
    }

    @GetMapping("/completed")
    public String completedTickets(Model model){
        model.addAttribute("completedTickets", ticketService.getTicketsByCompletion(true));
        return "completed-tickets";
    }

    @GetMapping("/add")
    public String addTicket(Model model){
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("units", unitService.getAllUnits());
        return "ticket-form";
    }

    //TODO - mechanism does not work properly, units should be fetched differently
    @PostMapping("/add")
    public String addTicketProcess(@ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors())
            return "redirect:/tickets/add";

        String previousUrl = request.getHeader("Referer");
        String trimmedPreviousUrl = previousUrl.split(request.getContextPath())[1];

        ticket.setDateNow();
        ticketService.saveTicket(ticket);
        return "redirect:/" + trimmedPreviousUrl;
    }

    /*
    @PostMapping("/add")
    public String addTicket(Model model){
        model.addAttribute("ticket", new Ticket());
        return "ticket-form";
    }
     */

}
