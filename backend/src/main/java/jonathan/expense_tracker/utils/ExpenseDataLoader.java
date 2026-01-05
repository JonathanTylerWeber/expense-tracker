package jonathan.expense_tracker.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jonathan.expense_tracker.model.Expense;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Component
public class ExpenseDataLoader {

  private static List<Expense> expenses = new ArrayList<>();

  @PostConstruct
  public void init(){
    ObjectMapper mapper = new ObjectMapper();
    InputStream is = getClass().getResourceAsStream("/expenses.json");

    try {
      expenses = mapper.readValue(is, new TypeReference<List<Expense>>(){
      
      });
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    
  }

  public static List<Expense> getExpenses() {
    return expenses;
  }

}
