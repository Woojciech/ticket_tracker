package com.suszkolabs.controller;

import com.suszkolabs.service.TicketService;
import com.suszkolabs.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UnitService unitService;

    @RequestMapping("/dashboard")
    public String showDashboard(){
        return "dashboard";
    }

}
