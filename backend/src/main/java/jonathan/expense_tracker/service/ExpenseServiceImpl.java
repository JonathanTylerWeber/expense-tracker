package jonathan.expense_tracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jonathan.expense_tracker.model.AppUser;
import jonathan.expense_tracker.model.Expense;
import jonathan.expense_tracker.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

  private final ExpenseRepository expenseRepository;
  private final UserService userService;

  public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserService userService) {
    this.expenseRepository = expenseRepository;
    this.userService = userService;
  }


  @Override
  public List<Expense> getAllUserExpenses(Long userId) {
    return new ArrayList<>(expenseRepository.findByUserIdOrderByDateDesc(userId));
  }

  @Override
  public List<Expense> getExpenseByDay(String date, Long userId) {
    return expenseRepository.findByUserIdOrderByDateDesc(userId)
    .stream()
    .filter(expense -> 
      expense.getDate().equalsIgnoreCase(date))
    .toList();
  }

  @Override
  public List<Expense> getExpenseByCategoryAndMonth(String category, String month, Long userId) {
    return expenseRepository.findByUserIdOrderByDateDesc(userId)
      .stream()
      .filter(expense -> expense
        .getCategory().equalsIgnoreCase(category) && 
        expense.getDate().startsWith(month))
      .toList();
  }

  @Override
  public List<String> getAllExpenseCategories(Long userId) {
    return expenseRepository.findByUserIdOrderByDateDesc(userId) // get all user expenses
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
  public Optional<Expense> getExpenseById(Long id, Long userId) {
    return expenseRepository.findByIdAndUserId(id, userId);
  }

  @Override
  public Expense addExpense(Expense expense, Long userId) {
    Optional<AppUser> userOptional = userService.findUserById(userId);

    if (userOptional.isPresent()) {
      AppUser user = userOptional.get();
      expense.setUser(user);
      return expenseRepository.save(expense);
    } else {
      throw new RuntimeException("User not found");
    }
  }

  @Override
  public boolean updateExpense(Expense updatedExpense, Long userId) {
    Optional<Expense> existingExpense = 
      expenseRepository.findByIdAndUserId(updatedExpense.getId(), userId);
      
    if(existingExpense.isPresent()) {
      updatedExpense.setUser(existingExpense.get().getUser());
      expenseRepository.save(updatedExpense);
      return true;
    } 
    
    return false;
  }

  @Override
  public boolean deleteExpense(Long id, Long userId) {
    Optional<Expense> existingExpense = 
      expenseRepository.findByIdAndUserId(id, userId);
    
    if (existingExpense.isPresent()) {
      expenseRepository.deleteById(id);
      return true;
    }

    return false;
  }

}
