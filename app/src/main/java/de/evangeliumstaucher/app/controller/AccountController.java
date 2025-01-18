package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.config.AccountConfig;
import de.evangeliumstaucher.app.model.Player;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.entity.PlayerEntity;
import de.evangeliumstaucher.repo.GameSessionRepository;
import de.evangeliumstaucher.repo.service.Library;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@Slf4j

public class AccountController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @Autowired
    AccountConfig accountConfig;

    public AccountController(Library library, GameSessionRepository gameSessionRepository) {
        super(library, gameSessionRepository);
    }

    @PostMapping("/createuser")
    public RedirectView createuser(@AuthenticationPrincipal OAuth2User oidcUser, @RequestParam @Nonnull String username, @RequestParam(required = false, name = "forwardTo") String forwardTo, Model m) {
        RedirectView out = new RedirectView();
        Player player = new Player(null, username, oidcUser.getAttribute("email"));
        if (!userService.valid(username)) {
            out.setUrl("/signup");
            out.getAttributesMap().put("warning", "Der Name wird schon verwendet. Verwende bitte einen anderen.");
            return out;
        } else {
            userService.create(player);
            out.setUrl(forwardTo);
            return out;
        }
    }

    @PostMapping("/changename")
    public RedirectView changename(@AuthenticationPrincipal OAuth2User oidcUser, @RequestParam @Nonnull String username, @RequestParam(required = false, name = "forwardTo") String forwardTo, Model m) {
        RedirectView out = new RedirectView();
        PlayerEntity player = (userService.getByEMail(oidcUser.getAttribute("email")).get());

        if (!userService.valid(username)) {
            out.setUrl("/edituser");
            out.getAttributesMap().put("warning", "Der Name wird schon verwendet. Verwende bitte einen anderen.");
            return out;
        } else {
            player.setUsername(username);
            userService.save(player);
            if (StringUtils.isEmpty(forwardTo)) {
                out.setUrl("/");
            } else {
                out.setUrl(forwardTo);
            }
            return out;
        }
    }

    @GetMapping("/edituser")
    public String edituser(Model m, @RequestParam(required = false, name = "error") String error, @RequestParam(required = false, name = "forwardTo") String forwardTo) {
        m.addAttribute("userPersists", true);

        if (error != null) {
            m.addAttribute("errortext", error);
        }
        String actionURL = "/changename";
        if (StringUtils.isNotEmpty(forwardTo)) {
            actionURL += "?forwardTo=" + URLEncoder.encode(forwardTo, StandardCharsets.UTF_8);
        }

        m.addAttribute("actionUrl", actionURL);
        return "signup.html";
    }

    @GetMapping("/signup")
    public String signup(Model m, @RequestParam(required = false, name = "error") String error, @RequestParam(required = false, name = "forwardTo") String forwardTo) {
        if (error != null) {
            m.addAttribute("errortext", error);
        }
        String actionURL = "/createuser";
        if (StringUtils.isNotEmpty(forwardTo)) {
            actionURL += "?forwardTo=" + URLEncoder.encode(forwardTo, StandardCharsets.UTF_8);
        }

        m.addAttribute("actionUrl", actionURL);
        return "signup.html";
    }

}
