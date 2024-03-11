package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.config.AccountConfig;
import de.evangeliumstaucher.app.model.Player;
import de.evangeliumstaucher.app.service.ApiServices;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.entity.PlayerEntity;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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

    public AccountController(ApiServices apiServices) {
        super(apiServices);
    }

    @PostMapping("/createuser")
    public RedirectView createuser(@AuthenticationPrincipal OidcUser oidcUser, @RequestParam @Nonnull String username, @RequestParam(required = false, name = "forwardTo") String forwardTo, Model m) {
        RedirectView out = new RedirectView();
        Player player = new Player(null, username, oidcUser.getEmail());
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
    public RedirectView changename(@AuthenticationPrincipal OidcUser oidcUser, @RequestParam @Nonnull String username, @RequestParam(required = false, name = "forwardTo") String forwardTo, Model m) {
        RedirectView out = new RedirectView();
        PlayerEntity player = (userService.getByEMail(oidcUser.getEmail()).get());

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
