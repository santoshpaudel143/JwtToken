package com.example.JwtToken.service;

import com.example.JwtToken.config.JwtTokenUtil;
import com.example.JwtToken.dto.JWTTokenRequestDto;
import com.example.JwtToken.dto.JWTTokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/** @Author: Santosh Paudel */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private final JwtTokenUtil jwtTokenUtil;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if ("opcrs".equals(username)) {
      return new User(
          "opcrs",
          "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
          new ArrayList<>());
    } else {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
  }

  public JWTTokenResponseDto getTokenForUser(JWTTokenRequestDto jwtTokenRequestDto) {

    final UserDetails userDetails = loadUserByUsername(jwtTokenRequestDto.getUsername());
    String token = jwtTokenUtil.generateToken(userDetails);
    JWTTokenResponseDto jwtTokenResponseDto = new JWTTokenResponseDto();
    jwtTokenResponseDto.setAccess_token(token);
    jwtTokenResponseDto.setRefresh_token(jwtTokenUtil.generateRefreshToken(userDetails));
    jwtTokenResponseDto.setExpires_in(jwtTokenUtil.getExpirationDateFromToken(token));

    return jwtTokenResponseDto;
  }
}
