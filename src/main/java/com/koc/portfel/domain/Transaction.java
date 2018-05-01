package com.koc.portfel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Transaction {

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


}
