package com.suszkolabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @RequestMapping("/dashboard")
    public String showDashboard(){
        return "dashboard";
    }

}
