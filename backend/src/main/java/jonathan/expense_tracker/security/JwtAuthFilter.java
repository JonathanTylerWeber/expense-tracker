package jonathan.expense_tracker.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jonathan.expense_tracker.service.UserDetailsServiceImpl;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private JwtUtil jwtUtil;
  private UserDetailsServiceImpl userDetailsService;

  public JwtAuthFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    String username = null;
    String token = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring(7);

      try {
        username = jwtUtil.extractUsername(token);
      } catch (Exception e) {
        System.out.println("Error extracting username from JWT");
      }
    } else {
      System.out.println("JWT doesn't begin with bearer string");
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      var userDetails = userDetailsService.loadUserByUsername(username);
      if (jwtUtil.validateToken(token, userDetails.getUsername())) {
        // create auth object and set it in the SecurityContext
        var authenticationToken = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
        
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    filterChain.doFilter(request, response);

  }
}
