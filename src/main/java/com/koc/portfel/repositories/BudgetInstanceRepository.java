package com.koc.portfel.repositories;

import com.koc.portfel.domain.BudgetInstance;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface BudgetInstanceRepository extends CrudRepository<BudgetInstance, Long> {
    Set<BudgetInstance> findAll();
}
