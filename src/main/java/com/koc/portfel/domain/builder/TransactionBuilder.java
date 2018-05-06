package com.koc.portfel.domain.builder;

import com.koc.portfel.domain.BudgetInstance;
import com.koc.portfel.domain.ExpenseCategory;
import com.koc.portfel.domain.Transaction;

import java.math.BigDecimal;

public class TransactionBuilder {
    private BigDecimal amount;
    private ExpenseCategory category;
    private BudgetInstance budgetInstance;

    private TransactionBuilder () {
    }

    public static TransactionBuilder create() {
        return new TransactionBuilder();
    }

    public TransactionBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public TransactionBuilder cat(ExpenseCategory category) {
        this.category = category;
        return this;
    }

    public TransactionBuilder applyTo(BudgetInstance budgetInstance) {
        this.budgetInstance = budgetInstance;
        return this;
    }

    public Transaction build() {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCategory(category);
        transaction.setBudgetInstance(budgetInstance);
        return transaction;
    }
}
