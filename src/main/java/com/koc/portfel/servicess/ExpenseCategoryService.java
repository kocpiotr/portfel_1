package com.koc.portfel.servicess;

import com.koc.portfel.domain.Budget;
import com.koc.portfel.domain.ExpenseCategory;

import java.util.Set;

public interface ExpenseCategoryService {
    Set<ExpenseCategory> getCategoriesForBudget(Budget budget);
}
