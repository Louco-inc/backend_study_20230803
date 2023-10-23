package com.example.backend_study_20230803.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class ViewController {

  @GetMapping("/index")
  public String index(Model model) {
    return "index";
  }

}
