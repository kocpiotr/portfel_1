package com.koc.portfel.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
public class BudgetInstance implements Comparable<BudgetInstance> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Budget budgetTemplate;

    @OneToMany(mappedBy = "budgetInstance", cascade = CascadeType.ALL)
    @OrderBy("id desc ")
    private SortedSet<Transaction> transactions = new TreeSet<>();

    private String name;
    private LocalDate periodStart;
    private LocalDate periodEnd;

    @Override
    public int compareTo(BudgetInstance theOther) {
        return periodStart.compareTo(theOther.getPeriodStart());
    }
}
