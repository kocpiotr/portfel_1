package com.koc.portfel.servicess;

import com.koc.portfel.domain.BudgetItem;

public interface BudgetItemSerice {
    void delete(BudgetItem itemToDelete);
    void delete(Long budgetItemToDeleteId);
}
