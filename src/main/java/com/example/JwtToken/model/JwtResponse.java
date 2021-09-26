package com.example.JwtToken.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** @Author: Santosh Paudel */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

  private String jwttoken;
}
