package jonathan.expense_tracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import jonathan.expense_tracker.model.Expense;
import jonathan.expense_tracker.repository.ExpenseRepository;

@Service
@Profile("db")
public class ExpenseServiceImplDb implements ExpenseService {

  private final ExpenseRepository expenseRepository;

  public ExpenseServiceImplDb(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public List<Expense> getExpenseByDay(String date) {
    return expenseRepository.findAll()
    .stream()
    .filter(expense -> expense.getDate().equalsIgnoreCase(date))
    .toList();
  }

  @Override
  public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
    return expenseRepository.findAll()
      .stream()
      .filter(expense -> expense
        .getCategory().equalsIgnoreCase(category) && 
        expense.getDate().startsWith(month))
      .toList();
  }

  @Override
  public List<String> getAllExpenseCategories() {
    return expenseRepository.findAll() // get all expenses
        .stream() // converts the list of expenses into a stream. a stream is an abstraction that allows you to perform a 
        // sequence of operations (filtering, mapping, etc) on elements of a collection
        .map( // the map operation is used to transform elements in the stream. here it takes each expense object
          // and transforms it by applying the getCategory() method to each one
          Expense::getCategory)
          //Expense::getCategory is a method reference, shorthand for (expense) -> expense.getCategories()
        .distinct() // this operation removes duplicates from the stream
        .toList(); // used to gather all the unique categories into a list and assign it to the variable categories
  }

  @Override
  public Optional<Expense> getExpenseById(Long id) {
    return expenseRepository.findById(id);
  }

  @Override
  public Expense addExpense(Expense expense) {
    return expenseRepository.save(expense);
  }

  @Override
  public boolean updateExpense(Expense updatedExpense) {
    if (expenseRepository.existsById(updatedExpense.getId())) {
      expenseRepository.save(updatedExpense);
      return true;
    }
    return false;
  }

  @Override
  public boolean deleteExpense(Long id) {
    if (expenseRepository.existsById(id)) {
      expenseRepository.deleteById(id);
      return true;
    }
    return false;
  }

}
