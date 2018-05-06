package com.koc.portfel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class BudgetItem implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private ExpenseCategory category;
    @ManyToOne
    private Budget owningBudget;
    private BigDecimal maxAmount;

    public void setOwningBudget(Budget owningBudget) {
        this.owningBudget = owningBudget;
        owningBudget.getBudgetItems().add(this);
    }

    //-------------------------------- IMPLEMENTED METHODS ----------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BudgetItem that = (BudgetItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(category, that.category) &&
                Objects.equals(maxAmount, that.maxAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, category, maxAmount);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof BudgetItem) {
            BudgetItem theOther = (BudgetItem) o;
            return this.getCategory().getName().compareTo(theOther.getCategory().getName());
        }
        return 0;
    }

    protected BudgetItem cloneForGivenOwningBudget(Budget owningBudget) {
        BudgetItem clone = new BudgetItem();
        clone.setCategory(getCategory());
        clone.setOwningBudget(owningBudget);
        clone.setMaxAmount(new BigDecimal(getMaxAmount().toEngineeringString()));

        return clone;
    }
}
