package com.koc.portfel.bootstrap;

import com.koc.portfel.domain.*;
import com.koc.portfel.repositories.*;
import com.koc.portfel.servicess.BudgetInstanceService;
import com.koc.portfel.servicess.BudgetService;
import lombok.extern.java.Log;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Log
public class DataSetupBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ExpenseCategoryRepository expenseCategoryRepository;
    private FinancialUnitRepository financialUnitRepository;
    private PersonRepository personRepository;
    private TransactionRepository transactionRepository;
    private BudgetRepository budgetRepository;
    private BudgetInstanceService budgetInstanceService;
    private BudgetItemRepository budgetItemRepository;
    private BudgetService budgetService;

    public DataSetupBootstrap(ExpenseCategoryRepository expenseCategoryRepository, FinancialUnitRepository financialUnitRepository, PersonRepository personRepository, TransactionRepository transactionRepository, BudgetRepository budgetRepository, BudgetInstanceService budgetInstanceService, BudgetItemRepository budgetItemRepository, BudgetService budgetService) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.financialUnitRepository = financialUnitRepository;
        this.personRepository = personRepository;
        this.transactionRepository = transactionRepository;
        this.budgetRepository = budgetRepository;
        this.budgetInstanceService = budgetInstanceService;
        this.budgetItemRepository = budgetItemRepository;
        this.budgetService = budgetService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        setupExpenseCategories();
        setupFinancialUnits();
        setupPersons();
        setupBudgets();
        setupBudgetInstance();
        setupTransationcs();
    }

    private void setupBudgetInstance() {
        BudgetInstance budgetInstance = new BudgetInstance();
        budgetInstance.setName("Obecny");
        budgetInstance.setPeriodStart(LocalDate.now().withDayOfMonth(1));
        budgetInstance.setPeriodEnd(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()));

        budgetInstanceService.save(budgetInstance);
    }

    private void setupBudgets() {
        Budget budget = new Budget();

        BudgetItem item_01 = new BudgetItem();
        ExpenseCategory gaz = expenseCategoryRepository.findByName("Gaz");
        item_01.setCategory(gaz);
        item_01.setMaxAmount(new BigDecimal(120));

        item_01.setOwningBudget(budget);

        BudgetItem item_02 = new BudgetItem();
        ExpenseCategory woda = expenseCategoryRepository.findByName("Woda");
        item_02.setCategory(woda);
        item_02.setMaxAmount(new BigDecimal(50));
        item_02.setOwningBudget(budget);

        budgetRepository.save(budget);
        budgetItemRepository.save(item_01);
        budgetItemRepository.save(item_02);

    }

    private void setupExpenseCategories() {
        final List<ExpenseCategory> expenseCategories = new ArrayList<>();
        expenseCategories.add(new ExpenseCategory("Czynsz Mieszkaniowy"));
        expenseCategories.add(new ExpenseCategory("Prąd"));
        expenseCategories.add(new ExpenseCategory("Gaz"));
        expenseCategories.add(new ExpenseCategory("Woda"));
        expenseCategories.add(new ExpenseCategory("Ogrzewanie"));
        expenseCategories.add(new ExpenseCategory("Scieki"));
        expenseCategories.add(new ExpenseCategory("Telefon"));
        expenseCategories.add(new ExpenseCategory("Internet"));
        expenseCategories.add(new ExpenseCategory("Rata kredytu"));
        expenseCategories.add(new ExpenseCategory("Ubezpieczenia"));
        expenseCategories.add(new ExpenseCategory("Inne Rachunki"));
        expenseCategories.add(new ExpenseCategory("Żywność"));
        expenseCategories.add(new ExpenseCategory("Paliwo"));
        expenseCategories.add(new ExpenseCategory("Bilety"));
        expenseCategories.add(new ExpenseCategory("Odzież"));
        expenseCategoryRepository.saveAll(expenseCategories);
    }

    private void setupFinancialUnits() {
        final Set<FinancialUnit> units = new HashSet<>();
        units.add(new FinancialUnit("Konto Gosia"));
        units.add(new FinancialUnit("Konto Piotr"));
        units.add(new FinancialUnit("Konto Oszczędnościowe"));
        units.add(new FinancialUnit("Portfel Gosia"));
        units.add(new FinancialUnit("Portfel Piotr"));
        units.add(new FinancialUnit("Oszczedności"));
        financialUnitRepository.saveAll(units);
    }

    private void setupPersons() {
        final Set<Person> persons = new HashSet<>();
        Person p1 = new Person();
        p1.setFirstName("Małgorzata");
        p1.setLastName("Koc");
        persons.add(p1);

        Person p2 = new Person();
        p2.setFirstName("Piotr");
        p2.setLastName("Koc");
        persons.add(p2);

        personRepository.saveAll(persons);
    }

    private void setupTransationcs() {
        final Set<Transaction> transactions = new HashSet<>();
        final ExpenseCategory gaz = expenseCategoryRepository.findByName("Gaz");
        final BudgetInstance currentBudgetInstnace = budgetInstanceService.getCurrentBudgetInstance();
        final Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(10));
        transaction.setCategory(gaz);
        transaction.setBudgetInstance(currentBudgetInstnace);

        transactions.add(transaction);
        transactionRepository.saveAll(transactions);
    }
}
