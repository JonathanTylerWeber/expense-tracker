package jonathan.expense_tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private AppUser user;


  
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

  public AppUser getUser() {
    return user;
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

  public void setUser(AppUser user) {
    this.user = user;
  }
}
