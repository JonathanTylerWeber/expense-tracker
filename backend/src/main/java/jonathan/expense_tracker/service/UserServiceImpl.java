package jonathan.expense_tracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jonathan.expense_tracker.model.AppUser;
import jonathan.expense_tracker.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public AppUser saveUser(AppUser appUser) {
    return userRepository.save(appUser);
  }

  @Override
  public AppUser findByUsername(String username) {
    return userRepository.findByUsername(username).orElse(null);
  }

  @Override
  public Optional<AppUser> findUserById(Long id) {
    return userRepository.findById(id);
  }

}
