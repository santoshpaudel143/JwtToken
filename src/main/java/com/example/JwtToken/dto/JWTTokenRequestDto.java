package com.example.JwtToken.dto;

import lombok.Data;

/** @Author: Santosh Paudel */

@Data
public class JWTTokenRequestDto {

  private String username;
  private String password;
}
