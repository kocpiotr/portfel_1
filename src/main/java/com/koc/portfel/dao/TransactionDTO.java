package com.koc.portfel.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionDTO {

    private Long id;
    private BigDecimal amount;
    private String description;
    private ExpenseCategoryDTO category;
    private FinancialUnitDTO chargedUnit;
    private FinancialUnitDTO recipiedUnit;
    private PersonDTO onRequest;
}
