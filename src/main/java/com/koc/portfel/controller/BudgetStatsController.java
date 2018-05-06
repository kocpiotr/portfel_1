package com.koc.portfel.controller;

import com.koc.portfel.dao.BudgetInstanceStatsDTO;
import com.koc.portfel.servicess.BudgetInstanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BudgetStatsController {

    BudgetInstanceService budgetInstanceService;

    public BudgetStatsController(BudgetInstanceService budgetInstanceService) {
        this.budgetInstanceService = budgetInstanceService;
    }

    @RequestMapping("/budget-stats-view")
    public String view() {
        return "budget-stats";
    }

    @ResponseBody
    @RequestMapping ("/budget-stats-get")
    public ResponseEntity getStats() {
        BudgetInstanceStatsDTO stats = budgetInstanceService.getStats();
        return new ResponseEntity(stats.getStats(), HttpStatus.OK);

    }
}
