package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final QuizService qService;

    @GetMapping("/")
    public String home(Model m) {
        m.addAttribute("info", "Der evangeliumstaucher befindet sich noch in der Entwicklung. Daher ist er nur eingeschränkt verfügbar.");
        return "index.html";
    }

    @RequestMapping(value = {"/robots.txt", "/robot.txt"})
    @ResponseBody
    public String getRobotsTxt() {
        return "User-agent: *\n" +
                "Disallow: /quiz/\n";
    }

    @RequestMapping(value = {"/about.html"})
    public String about() {
        return "about.html";
    }
}
