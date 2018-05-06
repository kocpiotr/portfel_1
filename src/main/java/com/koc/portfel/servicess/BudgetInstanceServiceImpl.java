package com.koc.portfel.servicess;

import com.koc.portfel.dao.BudgetInstanceStatsDTO;
import com.koc.portfel.domain.Budget;
import com.koc.portfel.domain.BudgetInstance;
import com.koc.portfel.repositories.BudgetInstanceRepository;
import com.koc.portfel.repositories.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BudgetInstanceServiceImpl implements BudgetInstanceService {

    private BudgetInstanceRepository budgetInstanceRepository;
    private BudgetRepository budgetRepository;
    private BudgetService budgetService;

    public BudgetInstanceServiceImpl(BudgetInstanceRepository budgetInstanceRepository, BudgetRepository budgetRepository, BudgetService budgetService) {
        this.budgetInstanceRepository = budgetInstanceRepository;
        this.budgetRepository = budgetRepository;
        this.budgetService = budgetService;
    }

    @Override
    public SortedSet<BudgetInstance> getAll() {
        final Set<BudgetInstance> allInstances = budgetInstanceRepository.findAll();
        final SortedSet<BudgetInstance> sortedInstances = allInstances.stream()
                .sorted(Comparator.comparing(BudgetInstance::getPeriodStart))
                .collect(Collectors.toCollection(() -> new TreeSet<>()));
        return sortedInstances;
    }

    @Override
    public BudgetInstance getFirst() {
        BudgetInstance first = budgetInstanceRepository.findAll().iterator().next();
        return first;
    }

    @Override
    public void save(BudgetInstance newInstnace) {
        populateBudgetTemplate(newInstnace);
        budgetInstanceRepository.save(newInstnace);
    }

    @Override
    public void delete(Long budgetInstanceToDelete) {
        final Optional<BudgetInstance> optionalBudgetInstance = budgetInstanceRepository.findById(budgetInstanceToDelete);
        if (optionalBudgetInstance.isPresent()) {
            budgetInstanceRepository.delete(optionalBudgetInstance.get());
        }
    }

    @Override
    public void update(BudgetInstance instanceToEdit) {
        final Optional<BudgetInstance> optionalInstance = budgetInstanceRepository.findById(instanceToEdit.getId());
        if(optionalInstance.isPresent()) {
            final BudgetInstance budgetInstance = optionalInstance.get();
            budgetInstance.setName(instanceToEdit.getName());
            budgetInstance.setPeriodStart(instanceToEdit.getPeriodStart());
            budgetInstance.setPeriodEnd(instanceToEdit.getPeriodEnd());
            budgetInstanceRepository.save(budgetInstance);
        }
    }

    @Override
    public BudgetInstance getCurrentBudgetInstance() {
        //TODO temp solution it should return budget market as current (only one current budget at time) rather than first....
        return budgetInstanceRepository.findAll().iterator().next();
    }

    @Override
    public BudgetInstanceStatsDTO buildStats() {
        final BudgetInstance currentBudgetInstance = getCurrentBudgetInstance();
        final Budget budgetTemplate = currentBudgetInstance.getBudgetTemplate();
        final BudgetInstanceStatsDTO stats = new BudgetInstanceStatsDTO();

        budgetTemplate.getBudgetItems().stream()
                .forEach(budgetItem -> stats.setCategoryLimit(budgetItem.getCategory(), budgetItem.getMaxAmount()));

        currentBudgetInstance.getTransactions().stream()
                .forEach(transaction -> stats.addStat(transaction.getCategory(), transaction.getAmount()));

        return stats;
    }

    private void populateBudgetTemplate(BudgetInstance newInstnace) {
        if (newInstnace.getBudgetTemplate() == null) {
            final Budget budget = budgetRepository.findAll().iterator().next();
            final Budget snapshot = budgetService.createSnapshot(budget);
            newInstnace.setBudgetTemplate(snapshot);
        }
    }
}
