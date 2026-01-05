package jonathan.expense_tracker.service;

import java.util.List;
import java.util.Optional;

import jonathan.expense_tracker.model.Expense;

public interface ExpenseService {
  List<Expense> getExpenseByDay(String date);
  List<Expense> getExpenseByCategoryAndMonth(String category, String month);
  List<String> getAllExpenseCategories();
  Optional<Expense> getExpenseById(Long Id);
  Expense addExpense(Expense expense);
  boolean updateExpense(Expense expense);
  boolean deleteExpense(Long id);
}
