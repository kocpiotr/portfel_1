package com.koc.portfel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin-view")
    public String transactionView() {
        return "admin-view";
    }
}
