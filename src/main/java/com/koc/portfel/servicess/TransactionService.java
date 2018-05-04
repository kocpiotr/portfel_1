package com.koc.portfel.servicess;

import com.koc.portfel.domain.Transaction;
import org.springframework.stereotype.Service;

public interface TransactionService {
    void save(Transaction transaction);
    void delete(Long transactionToDeleteID);
    void update(Transaction transactionToEdit);
}
