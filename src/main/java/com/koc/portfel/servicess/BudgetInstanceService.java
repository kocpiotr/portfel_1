package com.koc.portfel.servicess;

import com.koc.portfel.dao.BudgetInstanceStatsDTO;
import com.koc.portfel.domain.Budget;
import com.koc.portfel.domain.BudgetInstance;

import java.util.SortedSet;

public interface BudgetInstanceService {
    SortedSet<BudgetInstance> getAll();

    BudgetInstance getFirst();

    void save(BudgetInstance newInstnace);

    void delete(Long budgetInstanceToDelete);

    void update(BudgetInstance instanceToEdit);

    BudgetInstance getCurrentBudgetInstance();

    BudgetInstanceStatsDTO getStats();
}
