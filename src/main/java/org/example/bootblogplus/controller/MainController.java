package org.example.bootblogplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "행복한 세상의 수강생의 블로그");
        model.addAttribute("message", "행복한 세상의 수강생이란...");
        return "/index";
    }
}
