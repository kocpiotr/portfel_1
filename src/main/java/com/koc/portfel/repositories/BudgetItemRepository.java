package com.koc.portfel.repositories;

import com.koc.portfel.domain.BudgetItem;
import org.springframework.data.repository.CrudRepository;

public interface BudgetItemRepository extends CrudRepository<BudgetItem, Long> {
}
