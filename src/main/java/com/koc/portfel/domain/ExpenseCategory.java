package com.koc.portfel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class ExpenseCategory implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public ExpenseCategory(final String name) {
    this.name = name;
    }

    //--------------------------------------------IMPLEMENTED METHODS-------------------------------------------

    @Override
    public int compareTo(Object o) {
        if(o instanceof ExpenseCategory) {
            ExpenseCategory theOther = (ExpenseCategory) o;
            return this.getName().compareTo(theOther.getName());
        }

        return 0;
    }
}
