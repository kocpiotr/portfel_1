package com.koc.portfel.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor

public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "owningBudget", fetch = FetchType.EAGER)
    @OrderBy("category ASC")
    private SortedSet<BudgetItem> budgetItems = new TreeSet<>();

    //--------------------------- IMPLEMENTED METHODS ------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Budget budget = (Budget) o;
        return Objects.equals(id, budget.id) &&
                Objects.equals(budgetItems, budget.budgetItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                '}';
    }

    @Override
    public Budget clone() {
        final Budget clone = new Budget();
        final SortedSet<BudgetItem> budgetItems = clone.getBudgetItems();
        for (BudgetItem budgetItem: getBudgetItems()) {
            final BudgetItem clonedBudgetItem = budgetItem.cloneForGivenOwningBudget(clone);
            budgetItems.add(clonedBudgetItem);
        }
        return clone;
    }
}
