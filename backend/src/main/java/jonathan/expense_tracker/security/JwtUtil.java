package jonathan.expense_tracker.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

  private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

  public String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
      .claims(claims)
      .subject(username)
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
      .signWith(SECRET_KEY)
      .compact();
  }

  public String extractUsername(String token) {
    return extractAllClaims(token).getSubject();
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser()
      .verifyWith(SECRET_KEY) // verify secret key, data hasnt been modified
      .build() // build the parser after configuring it with secret key
      .parseSignedClaims(token) // decodes jwt and extracts claims (data in token)
      .getPayload(); // retrieves payload which has claims containing user id, role, expiration, etc
  }

  private boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
  }

  // method to validate the JWT token by checking the username and expiration
  public boolean validateToken(String token, String username) {
    final String extractedUsername = extractUsername(token);
    return (extractedUsername.equals(username) && !isTokenExpired(token));
  }

}
