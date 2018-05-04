package com.koc.portfel.servicess;

import com.koc.portfel.domain.Budget;
import com.koc.portfel.domain.BudgetItem;
import com.koc.portfel.repositories.BudgetItemRepository;
import com.koc.portfel.repositories.BudgetRepository;
import org.springframework.stereotype.Service;

@Service
public class BudgetServiceImpl implements BudgetService {

    private BudgetRepository budgetRepository;
    private BudgetItemRepository budgetItemRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository, BudgetItemRepository budgetItemRepository) {
        this.budgetRepository = budgetRepository;
        this.budgetItemRepository = budgetItemRepository;
    }

    @Override
    public Budget getCurrentBudget() {
        final Budget budget = budgetRepository.findAll().iterator().next();
        return budget;
    }

    @Override
    public boolean addBudgetItemToBudget(BudgetItem budgetItem, Budget owning) {
        budgetItem.setOwningBudget(owning);
        budgetItemRepository.save(budgetItem);
        budgetRepository.save(owning);
        return true;
    }

    @Override
    public Budget createSnapshot(Budget budget) {
        final Budget snapshot = budget.clone();
        budgetRepository.save(snapshot);
        return snapshot;
    }


}
