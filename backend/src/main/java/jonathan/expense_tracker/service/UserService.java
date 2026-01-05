package jonathan.expense_tracker.service;

import jonathan.expense_tracker.model.AppUser;

public interface UserService {
  AppUser saveUser(AppUser appUser);
  AppUser findByUsername(String username);
}
