package jonathan.expense_tracker.controller;

import org.springframework.web.bind.annotation.RestController;

import jonathan.expense_tracker.dto.AppUserDTO;
import jonathan.expense_tracker.dto.AuthDTO;
import jonathan.expense_tracker.dto.AuthResponseDTO;
import jonathan.expense_tracker.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/signup")
  public ResponseEntity<AuthResponseDTO> signup(@RequestBody AppUserDTO appUserDTO) {
    AuthResponseDTO response = authService.registerUser(appUserDTO);
    
    if ("success".equals(response.getMessage())) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.badRequest().body(response);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthDTO authDTO) {
    AuthResponseDTO response = authService.loginUser(authDTO);
    
    if ("success".equals(response.getMessage())) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.badRequest().body(response);
    }

  }
  

}
