package jonathan.expense_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int expenseType;
  private String date;
  private double amount;
  private String category;
  private String account;
  private String note;

  // --- Getters ---

  public Long getId() {
    return id;
  }

  public int getExpenseType() {
    return expenseType;
  }

  public String getDate() {
    return date;
  }

  public double getAmount() {
    return amount;
  }

  public String getCategory() {
    return category;
  }

  public String getAccount() {
    return account;
  }

  public String getNote() {
    return note;
  }

  // --- toString() ---

  @Override
  public String toString() {
    return "Expense{" +
        "id=" + id +
        ", expenseType=" + expenseType +
        ", date='" + date + '\'' +
        ", amount=" + amount +
        ", category='" + category + '\'' +
        ", account='" + account + '\'' +
        ", note='" + note + '\'' +
        '}';
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setExpenseType(int expenseType) {
    this.expenseType = expenseType;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public void setNote(String note) {
    this.note = note;
  }
}
