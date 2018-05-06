package com.koc.portfel.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.koc.portfel.domain.ExpenseCategory;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({"categoryName", "percentUsed"})
@EqualsAndHashCode
public class ExpenseCategoryStatDTO implements Serializable, Comparable<ExpenseCategoryStatDTO> {
    public static final BigDecimal HUNDRED = new BigDecimal(100);

    private BigDecimal currentValue = BigDecimal.ZERO;
    private BigDecimal maxValue;
    private ExpenseCategory category;

    public ExpenseCategoryStatDTO(final ExpenseCategory category, BigDecimal maxValue) {
        this.category = category;
        this.maxValue = maxValue;
    }

    public String getCategoryName() {
        return category.getName();
    }

    public BigDecimal getPercentUsed(){
        return currentValue.divide(maxValue.divide(HUNDRED), new MathContext(2, RoundingMode.HALF_UP)).setScale(2);
    }

    public void addToCurrentValue(final BigDecimal addition) {
        currentValue = currentValue.add(addition);
    }

    @Override
    public int compareTo(ExpenseCategoryStatDTO other) {
        int comparision = (this.getPercentUsed().intValue() - other.getPercentUsed().intValue()) * (-1);
        if (comparision == 0) {
            comparision = this.getCategoryName().compareTo(other.getCategoryName());
        }
        return comparision;
    }
}
