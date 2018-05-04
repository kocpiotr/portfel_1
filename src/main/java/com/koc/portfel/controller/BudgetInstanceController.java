package com.koc.portfel.controller;

import com.koc.portfel.domain.BudgetInstance;
import com.koc.portfel.servicess.BudgetInstanceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.SortedSet;

@Controller
public class BudgetInstanceController {

    private BudgetInstanceService budgetInstanceService;

    public BudgetInstanceController(BudgetInstanceService budgetInstanceService) {
        this.budgetInstanceService = budgetInstanceService;
    }

    @RequestMapping("/budget-instances-view")
    public String budgetInstancesView(final Model model) {
        final SortedSet<BudgetInstance> instances = budgetInstanceService.getAll();
        model.addAttribute("instances", instances);
        model.addAttribute("emptyBudgetInstance", new BudgetInstance());
        model.addAttribute("budgetInstanceToEdit", new BudgetInstance());

        return "budget-instances";
    }

    @RequestMapping(value = "/budget-insstance-add")
    public ModelAndView budgetPlanAdd(final @ModelAttribute BudgetInstance newInstnace) {
        budgetInstanceService.save(newInstnace);
        return new ModelAndView("redirect:/budget-instances-view");
    }

    @RequestMapping("/budget-instance-delete/{budgetInstanceToDelete}")
    public ModelAndView budgetPlanDelete(@PathVariable("budgetInstanceToDelete") Long budgetInstanceToDelete) {
        budgetInstanceService.delete(budgetInstanceToDelete);
        return new ModelAndView("redirect:/budget-instances-view");
    }

    @RequestMapping("/budget-instance-edit")
    public ModelAndView budgetPlanEdit(final @ModelAttribute BudgetInstance instanceToEdit) {
        budgetInstanceService.update(instanceToEdit);
        return new ModelAndView("redirect:/budget-instances-view");
    }

}
