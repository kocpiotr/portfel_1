package com.koc.portfel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FinancialUnitController {

    @RequestMapping(value = "/financial-unit-view")
    public String financialUnitView() {
        return "financial-unit-view";
    }
}
