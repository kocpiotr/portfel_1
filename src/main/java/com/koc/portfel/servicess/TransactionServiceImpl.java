package com.koc.portfel.servicess;

import com.koc.portfel.domain.Transaction;
import com.koc.portfel.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public void delete(Long transactionToDeleteID) {
        final Optional<Transaction> transactionOptional = transactionRepository.findById(transactionToDeleteID);
        if(transactionOptional.isPresent()) {
            transactionRepository.delete(transactionOptional.get());
        }
    }

    @Override
    public void update(Transaction transactionToEdit) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionToEdit.getId());
        if(transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            transaction.setCategory(transactionToEdit.getCategory());
            transaction.setAmount(transactionToEdit.getAmount());
            transaction.setDescription(transactionToEdit.getDescription());
            transactionRepository.save(transaction);
        }
    }
}
