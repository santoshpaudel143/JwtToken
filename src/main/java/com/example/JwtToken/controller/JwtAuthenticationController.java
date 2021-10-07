package com.example.JwtToken.controller;

import com.example.JwtToken.dto.JWTTokenRequestDto;
import com.example.JwtToken.service.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/** @Author: Santosh Paudel */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class JwtAuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final JwtUserDetailsService userDetailsService;

  @PostMapping("/token")
  public ResponseEntity<?> createAuthenticationToken(
      @RequestBody JWTTokenRequestDto jwtTokenRequestDto) throws Exception {

    authenticate(jwtTokenRequestDto.getUsername(), jwtTokenRequestDto.getPassword());
    return ResponseEntity.ok(userDetailsService.getTokenForUser(jwtTokenRequestDto));
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}
