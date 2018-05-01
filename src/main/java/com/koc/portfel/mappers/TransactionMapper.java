package com.koc.portfel.mappers;

import com.koc.portfel.dao.TransactionDTO;
import com.koc.portfel.domain.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionDTO transactionToTransactionDTO(Transaction transaction);
    Transaction transactionDTOtoTransaction(TransactionDTO transactionDTO);
}
