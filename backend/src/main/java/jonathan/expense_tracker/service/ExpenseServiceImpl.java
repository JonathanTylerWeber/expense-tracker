package jonathan.expense_tracker.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import jonathan.expense_tracker.model.Expense;
import jonathan.expense_tracker.utils.ExpenseDataLoader;

@Service
@Profile("json")
public class ExpenseServiceImpl implements ExpenseService {

  private static final AtomicLong idCounter = new AtomicLong();

  @Override
  public List<Expense> getExpenseByDay(String date) {
    return ExpenseDataLoader.getExpenses()
      .stream()
      .filter(expense -> expense.getDate().equalsIgnoreCase(date))
      .toList();
  }

  @Override
  public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
    return ExpenseDataLoader.getExpenses()
      .stream()
      .filter(expense -> expense
        .getCategory().equalsIgnoreCase(category) && 
        expense.getDate().startsWith(month))
      .toList();
  }

  @Override
  public List<String> getAllExpenseCategories() {
    return ExpenseDataLoader
        .getExpenses() // get all expenses
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
    return ExpenseDataLoader.getExpenses().stream().filter(
      expense -> expense.getId().equals(id)
    ).findFirst();
  }

  @Override
  public Expense addExpense(Expense expense) {
    expense.setId(idCounter.incrementAndGet());
    ExpenseDataLoader.getExpenses().add(expense);
    return expense;
  }

  @Override
  public boolean updateExpense(Expense updatedExpense) {
    Optional<Expense> existingExpense = getExpenseById(updatedExpense.getId());
    if (existingExpense.isPresent()) {
      ExpenseDataLoader.getExpenses().remove(existingExpense.get());
      ExpenseDataLoader.getExpenses().add(updatedExpense);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteExpense(Long id) {
    Optional<Expense> existingExpense = getExpenseById(id);
    if (existingExpense.isPresent()) {
      ExpenseDataLoader.getExpenses().remove(existingExpense.get());
      return true;
    } else {
      return false;
    }
  }

}
