package com.example.JwtToken.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** @Author: Santosh Paudel */
public class JwtResponse implements Serializable {
  private static final long serialVersionUID = -8091879091924046844L;
  private final String jwttoken;

  public JwtResponse(String jwttoken) {
    this.jwttoken = jwttoken;
  }

  public String getToken() {
    return this.jwttoken;
  }
}
