package com.suszkolabs.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tickets")
public class TicketController {

    @RequestMapping("/dashboard")
    public String showDashboard(){
        return "dashboard";
    }

}
