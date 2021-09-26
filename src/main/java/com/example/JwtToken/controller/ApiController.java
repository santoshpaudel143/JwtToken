package com.example.JwtToken.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @Author: Santosh Paudel */
@RestController
@RequestMapping("/api")
public class ApiController {

  @GetMapping("/test")
  public String testApi() {
    return "This api is working fine!!!";
  }
}
