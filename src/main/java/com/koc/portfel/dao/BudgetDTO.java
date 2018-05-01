package com.koc.portfel.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class BudgetDTO {
    private Long id;
    Set<BudgetItemDTO> budgetItems;
}
