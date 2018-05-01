package com.koc.portfel.mappers;

import com.koc.portfel.dao.BudgetDTO;
import com.koc.portfel.domain.Budget;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BudgetMapper {
    BudgetMapper INSTANCE = Mappers.getMapper(BudgetMapper.class);

    BudgetDTO budgetToBudgetDTO(Budget transaction);
}
