package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.config.AccountConfig;
import de.evangeliumstaucher.app.model.Player;
import de.evangeliumstaucher.app.service.ApiServices;
import de.evangeliumstaucher.app.service.SessionService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@Slf4j
public class AccountController extends BaseController {

    @Autowired
    SessionService sessionService;

    @Autowired
    HttpSession session;

    @Autowired
    AccountConfig accountConfig;

    public AccountController(ApiServices apiServices) {
        super(apiServices);
    }

    @PostMapping("/createuser")
    public @ResponseBody String createuser(@RequestBody String username, Model m) {
        Player player = new Player(null, username);
        sessionService.create(player);
        return "user created";
    }

    @GetMapping("/signup")
    public String signup(Model m, @RequestParam(required = false, name = "error") String error) {
        if (accountConfig.getWhitelist() != null) {
            m.addAttribute("warning", "Zurzeit sind nur ausgewählte Nutzer zur Nutzung autorisiert.");
        }
        if (error != null) {
            m.addAttribute("errortext", error);
        }
        return "signup.html";
    }

}
