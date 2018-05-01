package com.koc.portfel.servicess;

import com.koc.portfel.domain.BudgetItem;
import com.koc.portfel.repositories.BudgetItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BudgetItemSericeImpl implements BudgetItemSerice {

    private BudgetItemRepository budgetItemRepository;

    public BudgetItemSericeImpl(BudgetItemRepository budgetItemRepository) {
        this.budgetItemRepository = budgetItemRepository;
    }

    @Override
    public void delete(BudgetItem itemToDelete) {
        budgetItemRepository.delete(itemToDelete);
    }

    @Override
    public void delete(Long budgetItemToDeleteId) {
        Optional<BudgetItem> byId = budgetItemRepository.findById(budgetItemToDeleteId);
        budgetItemRepository.delete(byId.get());
    }
}
