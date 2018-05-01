package com.koc.portfel.dao;

import com.koc.portfel.domain.ExpenseCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class BudgetItemDTO {
    private Long id;

    private ExpenseCategoryDTO category;
    private BigDecimal maxAmount;

    private BudgetDTO owningBudget;
}
