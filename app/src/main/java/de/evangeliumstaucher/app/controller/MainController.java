package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final QuizService qService;
    private final UserService userService;

    @ModelAttribute("playerModel")
    @Nullable
    public PlayerModel PlayerModelAttribute(@AuthenticationPrincipal AuthenticatedPrincipal principal) {
        return userService.getPlayerModel(principal, userService);
    }

    @GetMapping("/")
    public String home(Model m) {
        //m.addAttribute("info", "Der evangeliumstaucher befindet sich noch in der Entwicklung. Daher ist er nur für ausgewählte Testnutzer verfügbar. Bewerbung als Testnutzer an edel.gesinnt.de@gmail.com.");
        return "index.html";
    }

    @RequestMapping(value = {"/robots.txt", "/robot.txt"},
            produces = {MediaType.TEXT_PLAIN_VALUE})
    @ResponseBody
    public String getRobotsTxt() {
        return "User-agent: *\n";
    }

    @RequestMapping(value = {"/about"})
    public String about() {
        return "about.html";
    }

    @RequestMapping(value = {"/privacy"})
    public String privacy() {
        return "privacy.html";
    }

    @RequestMapping(value = {"/terms-of-use"})
    public String termsOfUse() {
        return "terms-of-use.html";
    }
}
