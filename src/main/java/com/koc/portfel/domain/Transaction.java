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
public class Transaction implements Comparable<Transaction> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * positive or negative amount of operation.
     */
    private BigDecimal amount;

    /**
     * tell me more if you want.
     */
    private String description;

    /**
     * Expense category.
     */
    @ManyToOne
    private ExpenseCategory category;

    /**
     * Expense category.
     */
    @ManyToOne
    private BudgetInstance budgetInstance;

    /**
     * Who paid.
     */
    @ManyToOne
    private FinancialUnit chargedUnit;

    /**
     * Who get the money.
     */
    @ManyToOne
    private FinancialUnit recipiedUnit;

    /**
     * Who did trigger this transaction.
     */
    @ManyToOne
    private Person onRequest;

    public void setBudgetInstance(BudgetInstance budgetInstance) {
        this.budgetInstance = budgetInstance;
        budgetInstance.getTransactions().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, amount, description, category);
    }

    @Override
    public int compareTo(Transaction theOtherTransaction) {
        int result = 1;
        if(getId() != null && theOtherTransaction.getId() != null) {
            getId().compareTo(theOtherTransaction.getId());
        }
        return result;
    }
}
