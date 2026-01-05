package jonathan.expense_tracker.service;

import jonathan.expense_tracker.dto.AppUserDTO;
import jonathan.expense_tracker.dto.AuthDTO;
import jonathan.expense_tracker.dto.AuthResponseDTO;

public interface AuthService {
  AuthResponseDTO registerUser(AppUserDTO appUserDTO);
  AuthResponseDTO loginUser(AuthDTO authDTO);
}
