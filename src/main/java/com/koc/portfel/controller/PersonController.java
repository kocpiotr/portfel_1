package com.koc.portfel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonController {

    @RequestMapping(value = "/person-view")
    public String personView() {
        return "person-view";
    }
}
