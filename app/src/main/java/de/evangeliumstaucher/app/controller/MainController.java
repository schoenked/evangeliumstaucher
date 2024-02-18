package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final QuizService qService;

    @GetMapping("/")
    public String home(Model m) {
        m.addAttribute("info", "Der evangeliumstaucher befindet sich noch in der Entwicklung. Daher ist er nicht im vollem Umfang verfügbar.");
        return "index.html";
    }

}
