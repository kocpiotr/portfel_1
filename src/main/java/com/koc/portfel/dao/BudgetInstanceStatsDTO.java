package com.koc.portfel.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.koc.portfel.domain.ExpenseCategory;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class BudgetInstanceStatsDTO {
    private SortedMap<String, ExpenseCategoryStatDTO> statDTOMap = new TreeMap<>();

    public void setCategoryLimit(ExpenseCategory category, BigDecimal maxAmount) {
        ExpenseCategoryStatDTO expenseCategoryStatDTO = statDTOMap.get(category.getName());
        if(expenseCategoryStatDTO == null) {
            expenseCategoryStatDTO = new ExpenseCategoryStatDTO(category, maxAmount);
            statDTOMap.put(category.getName(), expenseCategoryStatDTO);
        }
    }

    public void addStat(ExpenseCategory category, BigDecimal amount) {
        ExpenseCategoryStatDTO expenseCategoryStatDTO = statDTOMap.get(category.getName());
        expenseCategoryStatDTO.addToCurrentValue(amount);
    }

    public Collection<ExpenseCategoryStatDTO> getStats() {
        return  statDTOMap.values();
    }

    public Set<ExpenseCategoryStatDTO> worst(int maxNumberOfResults) {
        final TreeSet<ExpenseCategoryStatDTO> sorted = new TreeSet<>(statDTOMap.values());


        final Set<ExpenseCategoryStatDTO> values = sorted.stream()
                .limit(maxNumberOfResults)
                .collect(Collectors.toCollection(() -> new TreeSet<>()));
        return values;
    }
}
