package com.koc.portfel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpenseCategoryController {

    @RequestMapping(value = "/expense-category-view")
    public String expenseCategoryView() {
        return "expense-category-view";
    }
}
