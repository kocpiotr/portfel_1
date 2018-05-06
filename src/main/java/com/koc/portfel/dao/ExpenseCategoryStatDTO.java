package com.koc.portfel.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.koc.portfel.domain.ExpenseCategory;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@EqualsAndHashCode
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class ExpenseCategoryStatDTO implements Serializable {
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
}
