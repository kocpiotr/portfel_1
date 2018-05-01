package com.koc.portfel.servicess;

import com.koc.portfel.domain.Budget;
import com.koc.portfel.domain.BudgetItem;

public interface BudgetService {
    Budget getCurrentBudget();
    boolean addBudgetItemToBudget(final BudgetItem budgetItem, final Budget owning);
}
