package com.koc.portfel.controller;

import com.koc.portfel.domain.Budget;
import com.koc.portfel.domain.BudgetItem;
import com.koc.portfel.domain.ExpenseCategory;
import com.koc.portfel.servicess.BudgetItemSerice;
import com.koc.portfel.servicess.BudgetService;
import com.koc.portfel.servicess.ExpenseCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class BudgetController {

    private BudgetService budgetService;
    private ExpenseCategoryService expenseCategoryService;
    private BudgetItemSerice budgetItemSerice;

    public BudgetController(BudgetService budgetService, ExpenseCategoryService expenseCategoryService, BudgetItemSerice budgetItemSerice) {
        this.budgetService = budgetService;
        this.expenseCategoryService = expenseCategoryService;
        this.budgetItemSerice = budgetItemSerice;
    }

    @RequestMapping(value = "/budget-plan-view")
    public String budgetPlanView(final Model model) {
        final Budget currentBudget = budgetService.getCurrentBudget();
        final Set<ExpenseCategory> availableCategoriesForBudget = expenseCategoryService.getCategoriesForBudget(currentBudget);

        model.addAttribute("budgetInstance", currentBudget);
        model.addAttribute("emptyBudgetItem", new BudgetItem());
        model.addAttribute("budgetItemToEdit", new BudgetItem());
        model.addAttribute("expenseCategories", availableCategoriesForBudget);

        return "budget-plan";
    }

    @RequestMapping(value = "/budget-plan-add")
    public ModelAndView budgetPlanAdd(final @ModelAttribute BudgetItem newItem) {
        budgetService.addBudgetItemToBudget(newItem, budgetService.getCurrentBudget());
        return new ModelAndView("redirect:/budget-plan-view");
    }

    @RequestMapping("/budget-plan-delete/{budgetItemToDelete}")
    public ModelAndView budgetPlanDelete(@PathVariable("budgetItemToDelete") Long budgetItemToDelete) {
        budgetItemSerice.delete(budgetItemToDelete);
        return new ModelAndView("redirect:/budget-plan-view");
    }

    @RequestMapping("/budget-plan-edit")
    public ModelAndView budgetPlanEdit(final @ModelAttribute BudgetItem itemToEdit) {
        budgetItemSerice.update(itemToEdit);
        return new ModelAndView("redirect:/budget-plan-view");
    }
}
