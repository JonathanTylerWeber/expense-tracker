package jonathan.expense_tracker.service;

import java.util.List;
import java.util.Optional;

import jonathan.expense_tracker.model.Expense;

public interface ExpenseService {
  List<Expense> getAllUserExpenses(Long userId);
  List<Expense> getExpenseByDay(String date, Long userId);
  List<Expense> getExpenseByCategoryAndMonth(String category, String month, Long userId);
  List<String> getAllExpenseCategories(Long userId);
  Optional<Expense> getExpenseById(Long Id, Long userId);
  Expense addExpense(Expense expense, Long userId);
  boolean updateExpense(Expense expense, Long userId);
  boolean deleteExpense(Long id, Long userId);
}
