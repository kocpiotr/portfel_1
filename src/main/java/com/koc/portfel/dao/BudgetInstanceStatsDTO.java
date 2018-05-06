package com.koc.portfel.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.koc.portfel.domain.ExpenseCategory;

import java.math.BigDecimal;
import java.util.*;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class BudgetInstanceStatsDTO {
    private Map<String, ExpenseCategoryStatDTO> statDTOMap = new HashMap<>();

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
}
