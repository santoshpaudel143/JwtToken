package com.example.JwtToken.dto;

import lombok.Data;

import java.util.Date;

/** @Author: Santosh Paudel */
@Data
public class JWTTokenResponseDto {

  private String access_token;
  private String token_type = "Bearer";
  private String refresh_token;
  private Date created_in = new Date();
  private Date expires_in;
}
