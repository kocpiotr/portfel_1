package com.koc.portfel.bootstrap;

import com.koc.portfel.domain.*;
import com.koc.portfel.domain.builder.TransactionBuilder;
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
        item_01.setMaxAmount(new BigDecimal(100));

        item_01.setOwningBudget(budget);

        BudgetItem item_02 = new BudgetItem();
        ExpenseCategory woda = expenseCategoryRepository.findByName("Woda");
        item_02.setCategory(woda);
        item_02.setMaxAmount(new BigDecimal(100));
        item_02.setOwningBudget(budget);

        BudgetItem item_03 = new BudgetItem();
        ExpenseCategory czynszMieszkaniowy = expenseCategoryRepository.findByName("Czynsz M.");
        item_03.setCategory(czynszMieszkaniowy);
        item_03.setMaxAmount(new BigDecimal(100));
        item_03.setOwningBudget(budget);

        BudgetItem item_04 = new BudgetItem();
        ExpenseCategory prad = expenseCategoryRepository.findByName("Prąd");
        item_04.setCategory(prad);
        item_04.setMaxAmount(new BigDecimal(100));
        item_04.setOwningBudget(budget);

        BudgetItem item_05 = new BudgetItem();
        ExpenseCategory telefon = expenseCategoryRepository.findByName("Telefon");
        item_05.setCategory(telefon);
        item_05.setMaxAmount(new BigDecimal(100));
        item_05.setOwningBudget(budget);

        BudgetItem item_06 = new BudgetItem();
        ExpenseCategory internet = expenseCategoryRepository.findByName("Internet");
        item_06.setCategory(internet);
        item_06.setMaxAmount(new BigDecimal(100));
        item_06.setOwningBudget(budget);

        BudgetItem item_07 = new BudgetItem();
        ExpenseCategory rataKredytu = expenseCategoryRepository.findByName("Rata kredytu");
        item_07.setCategory(rataKredytu);
        item_07.setMaxAmount(new BigDecimal(100));
        item_07.setOwningBudget(budget);

        BudgetItem item_08 = new BudgetItem();
        ExpenseCategory ubezpieczenia = expenseCategoryRepository.findByName("Ubezpieczenia");
        item_08.setCategory(ubezpieczenia);
        item_08.setMaxAmount(new BigDecimal(100));
        item_08.setOwningBudget(budget);

        BudgetItem item_09 = new BudgetItem();
        ExpenseCategory inneRachunki = expenseCategoryRepository.findByName("Inne Rachunki");
        item_09.setCategory(inneRachunki);
        item_09.setMaxAmount(new BigDecimal(100));
        item_09.setOwningBudget(budget);

        BudgetItem item_10 = new BudgetItem();
        ExpenseCategory zywnosc = expenseCategoryRepository.findByName("Żywność");
        item_10.setCategory(zywnosc);
        item_10.setMaxAmount(new BigDecimal(100));
        item_10.setOwningBudget(budget);

        BudgetItem item_11 = new BudgetItem();
        ExpenseCategory paliwo = expenseCategoryRepository.findByName("Paliwo");
        item_11.setCategory(paliwo);
        item_11.setMaxAmount(new BigDecimal(100));
        item_11.setOwningBudget(budget);

        BudgetItem item_12 = new BudgetItem();
        ExpenseCategory odziez = expenseCategoryRepository.findByName("Odzież");
        item_12.setCategory(odziez);
        item_12.setMaxAmount(new BigDecimal(100));
        item_12.setOwningBudget(budget);

        budgetRepository.save(budget);
        budgetItemRepository.save(item_01);
        budgetItemRepository.save(item_02);
        budgetItemRepository.save(item_03);
        budgetItemRepository.save(item_04);
        budgetItemRepository.save(item_05);
        budgetItemRepository.save(item_06);
        budgetItemRepository.save(item_07);
        budgetItemRepository.save(item_08);
        budgetItemRepository.save(item_09);
        budgetItemRepository.save(item_10);
        budgetItemRepository.save(item_11);
        budgetItemRepository.save(item_12);
    }

    private void setupExpenseCategories() {
        final List<ExpenseCategory> expenseCategories = new ArrayList<>();
        expenseCategories.add(new ExpenseCategory("Czynsz M."));
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

        final BudgetInstance currentBudgetInstnace = budgetInstanceService.getCurrentBudgetInstance();
        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(10))
                        .cat(expenseCategoryRepository.findByName("Gaz"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(20))
                        .cat(expenseCategoryRepository.findByName("Woda"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(30))
                        .cat(expenseCategoryRepository.findByName("Czynsz M."))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(40))
                        .cat(expenseCategoryRepository.findByName("Prąd"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(50))
                        .cat(expenseCategoryRepository.findByName("Telefon"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(60))
                        .cat(expenseCategoryRepository.findByName("Internet"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(70))
                        .cat(expenseCategoryRepository.findByName("Rata kredytu"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(80))
                        .cat(expenseCategoryRepository.findByName("Ubezpieczenia"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(90))
                        .cat(expenseCategoryRepository.findByName("Inne Rachunki"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(100))
                        .cat(expenseCategoryRepository.findByName("Żywność"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(110))
                        .cat(expenseCategoryRepository.findByName("Paliwo"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactions.add(
                TransactionBuilder.create()
                        .amount(new BigDecimal(120))
                        .cat(expenseCategoryRepository.findByName("Odzież"))
                        .applyTo(currentBudgetInstnace)
                        .build());

        transactionRepository.saveAll(transactions);
    }
}
