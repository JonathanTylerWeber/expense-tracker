package jonathan.expense_tracker.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jonathan.expense_tracker.model.AppUser;
import jonathan.expense_tracker.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    AppUser appUser = userRepository.findByUsername(username).orElseThrow(
      () -> new UsernameNotFoundException("User not found"));

    return new User(appUser.getUsername(), 
      appUser.getPassword(), 
      new ArrayList<>()); // for adding roles?
  }

}
