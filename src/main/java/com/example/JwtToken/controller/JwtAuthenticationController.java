package com.example.JwtToken.controller;

import com.example.JwtToken.config.JwtTokenUtil;
import com.example.JwtToken.dto.JWTTokenResponseDto;
import com.example.JwtToken.model.JwtRequest;
import com.example.JwtToken.service.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/** @Author: Santosh Paudel */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class JwtAuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  private final JwtUserDetailsService userDetailsService;

  @PostMapping("/token")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
      throws Exception {

    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    String token = jwtTokenUtil.generateToken(userDetails);
    JWTTokenResponseDto jwtTokenResponseDto = new JWTTokenResponseDto();
    jwtTokenResponseDto.setAccess_token(token);
    jwtTokenResponseDto.setRefresh_token(jwtTokenUtil.generateRefreshToken(userDetails));
    jwtTokenResponseDto.setExpires_in(jwtTokenUtil.getExpirationDateFromToken(token));

    return ResponseEntity.ok(jwtTokenResponseDto);
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
