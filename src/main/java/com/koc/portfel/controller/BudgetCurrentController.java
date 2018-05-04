package com.koc.portfel.controller;

import com.koc.portfel.domain.BudgetInstance;
import com.koc.portfel.domain.ExpenseCategory;
import com.koc.portfel.domain.Transaction;
import com.koc.portfel.repositories.BudgetInstanceRepository;
import com.koc.portfel.servicess.BudgetInstanceService;
import com.koc.portfel.servicess.ExpenseCategoryService;
import com.koc.portfel.servicess.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;

@Controller
public class BudgetCurrentController {

    private BudgetInstanceService budgetInstanceService;
    private BudgetInstanceRepository budgetInstanceRepository;
    private ExpenseCategoryService expenseCategoryService;
    private TransactionService transactionService;

    public BudgetCurrentController(BudgetInstanceService budgetInstanceService, BudgetInstanceRepository budgetInstanceRepository, ExpenseCategoryService expenseCategoryService, TransactionService transactionService) {
        this.budgetInstanceService = budgetInstanceService;
        this.budgetInstanceRepository = budgetInstanceRepository;
        this.expenseCategoryService = expenseCategoryService;
        this.transactionService = transactionService;
    }

    @RequestMapping("/budget-current-view")
    public String budgetCurrentView(final Model model) {
        final BudgetInstance instance = budgetInstanceService.getFirst();
        populateView(model, instance);
        return "budget-current";
    }

    @RequestMapping("/budget-current-view/{instanceID}")
    public String budgetCurrentView(final Model model, @PathVariable("instanceID") Long instanceID) {
        final BudgetInstance instance  = budgetInstanceRepository.findById(instanceID).get();
        populateView(model, instance);
        return "budget-current";
    }

    private void populateView(Model model, BudgetInstance instance) {
        final Set<ExpenseCategory> availableCategoriesForBudget = expenseCategoryService.getCategoriesForBudget(instance.getBudgetTemplate());

        model.addAttribute("instance", instance);
        model.addAttribute("emptyTransaction", new Transaction());
        model.addAttribute("expenseCategories", availableCategoriesForBudget);
        model.addAttribute("transactionToEdit", new Transaction());

    }

    @RequestMapping("/budget-current-transaction-add/{instanceID}")
    public ModelAndView budgetCurrentTransactionAdd(final @ModelAttribute Transaction transaction, @PathVariable("instanceID") Long instanceID) {
        final Optional<BudgetInstance> budgetInstanceOptional = budgetInstanceRepository.findById(instanceID);
        if(budgetInstanceOptional.isPresent()) {
            BudgetInstance budgetInstance = budgetInstanceOptional.get();
            transaction.setBudgetInstance(budgetInstance);

        }
        transactionService.save(transaction);
        return new ModelAndView("redirect:/budget-current-view");
    }

    @RequestMapping("/budget-current-delete/{transactionToDeleteID}")
    public ModelAndView budgetCurrentTransactionDelete(@PathVariable("transactionToDeleteID") Long transactionToDeleteID) {
        transactionService.delete(transactionToDeleteID);
        return new ModelAndView("redirect:/budget-current-view");
    }

    @RequestMapping("budget-current-edit")
    public ModelAndView budgetCurrentTransactionEdit(final @ModelAttribute Transaction transactionToEdit) {
        transactionService.update(transactionToEdit);
        return new ModelAndView("redirect:/budget-current-view");
    }
}
