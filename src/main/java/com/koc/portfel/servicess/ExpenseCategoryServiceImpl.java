package com.koc.portfel.servicess;

import com.koc.portfel.domain.Budget;
import com.koc.portfel.domain.BudgetInstance;
import com.koc.portfel.domain.BudgetItem;
import com.koc.portfel.domain.ExpenseCategory;
import com.koc.portfel.repositories.ExpenseCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    private ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategoryServiceImpl(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    @Override
    public Set<ExpenseCategory> getCategoriesForBudget(Budget budget) {
        //Set<ExpenseCategory> items = StreamSupport.stream(expenseCategoryRepository.findAll().spliterator(), false).collect(Collectors.toSet());
        final Set<ExpenseCategory> allExpenseItems = expenseCategoryRepository.findAll();
        final Set<ExpenseCategory> currectlyUsedExpenceCategories = getExpenseCategoriesUsedByBudget(budget);
        final SortedSet<ExpenseCategory> filteredExpenseItems = allExpenseItems.stream()
                .filter(expenseCategory -> !currectlyUsedExpenceCategories.contains(expenseCategory))
                .sorted(Comparator.comparing(ExpenseCategory::getName))
                .collect(Collectors.toCollection(() -> new TreeSet<>()));
        return filteredExpenseItems;
    }

    @Override
    public Set<ExpenseCategory> getCategoriesForBudgetInstance(BudgetInstance budgetInstance) {
        Budget budgetTemplate = budgetInstance.getBudgetTemplate();
        final Set<ExpenseCategory> currectlyUsedExpenceCategories = getExpenseCategoriesUsedByBudget(budgetTemplate);

        return currectlyUsedExpenceCategories;
    }

    private Set<ExpenseCategory> getExpenseCategoriesUsedByBudget(Budget budgetTemplate) {
        return budgetTemplate.getBudgetItems().stream()
                    .map(budgetItem -> budgetItem.getCategory())
                    .collect(Collectors.toSet());
    }
}
