package com.devrezaur.main.controller;

import com.devrezaur.main.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final QuizService qService;

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

}
