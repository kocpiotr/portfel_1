package com.koc.portfel.repositories;

import com.koc.portfel.domain.ExpenseCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface ExpenseCategoryRepository extends CrudRepository<ExpenseCategory, Long> {
    ExpenseCategory findByName(final String name);
    Set<ExpenseCategory> findAll();
}
