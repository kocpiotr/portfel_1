package com.koc.portfel.repositories;

import com.koc.portfel.domain.Budget;
import org.springframework.data.repository.CrudRepository;

public interface BudgetRepository extends CrudRepository<Budget, Long> {
}
