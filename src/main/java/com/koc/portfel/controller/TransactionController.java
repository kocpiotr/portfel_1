package com.koc.portfel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TransactionController {

    @RequestMapping(value = "/transaction-view")
    public String transactionView() {
        return "transaction-view";
    }
}
