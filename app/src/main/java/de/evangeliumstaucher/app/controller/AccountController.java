package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.config.AccountConfig;
import de.evangeliumstaucher.app.model.Player;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import de.evangeliumstaucher.entity.PlayerEntity;
import de.evangeliumstaucher.repo.GameSessionRepository;
import de.evangeliumstaucher.repo.service.Library;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.UUID;

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

    @ModelAttribute("playerModel")
    @Nullable
    public PlayerModel PlayerModelAttribute(@AuthenticationPrincipal AuthenticatedPrincipal principal) {
        return userService.getPlayerModel(principal, userService);
    }

    @PostMapping("/createuser")
    public RedirectView createuser(@AuthenticationPrincipal AuthenticatedPrincipal principal, @RequestParam @Nonnull String username, @RequestParam(required = false, name = "forwardTo") String forwardTo, Model m) {
        RedirectView out = new RedirectView();
        Player player = new Player(null, username, userService.getGlobalId(principal));
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
        if (oidcUser == null) {
            out = new RedirectView("?warning=" + URLEncoder.encode("Ein Gastbenutzer kann seinen Namen nicht ändern.", StandardCharsets.UTF_8));
            return out;
        }
        PlayerEntity player = (userService.getByGlobalId(userService.getGlobalId(oidcUser)).get());

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

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model m, @RequestParam(required = false, name = "error") String error, @RequestParam(required = false, name = "forwardTo") String forwardTo) {
        m.addAttribute("actionUrl", forwardTo);
        return "login";
    }

    @GetMapping("/login/guest")
    public RedirectView loginAsGuest(HttpServletRequest request, @RequestParam(required = false, name = "forwardTo") String forwardTo, Model m) {
        // 1. Erstelle einen Gast-User (oder lade existierenden basierend auf Session/Cookie Logik)
        // Hier als Beispiel generieren wir eine temporäre ID
        String guestId = "guest_" + UUID.randomUUID().toString().substring(0, 8);
        if (Strings.isEmpty(forwardTo)) {
            DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if (savedRequest != null) {
                forwardTo = savedRequest.getRequestURI();
                // Wichtig: Query-Parameter (z.B. ?id=5) nicht vergessen!
                if (StringUtils.isNotEmpty(savedRequest.getQueryString())) {
                    forwardTo += "?" + savedRequest.getQueryString();
                }
            }
        }
        // Optional: Gast im UserService anlegen/speichern, damit du Daten mappen kannst
        // userService.createGuestProfile(guestId);
        AuthenticatedPrincipal principal = () -> guestId;
        // 2. Erstelle das Authentication Objekt (WICHTIG: "ROLE_GUEST")
        // Wir nutzen UsernamePasswordAuthenticationToken, da es einfach ist.
        Authentication guestAuth = new UsernamePasswordAuthenticationToken(
                principal, // Principal (User ID oder Objekt)
                null,    // Credentials (keine nötig)
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_GUEST")) // Berechtigung
        );

        // 3. Manuell in den Security Context setzen
        SecurityContext sc = SecurityContextHolder.createEmptyContext();
        sc.setAuthentication(guestAuth);
        SecurityContextHolder.setContext(sc);

        // 4. Den Context explizit in der Session speichern
        // Das ist notwendig, damit Spring Security den User beim nächsten Request wiedererkennt!
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

        return createuser(principal, guestId, forwardTo, m);
    }

    @GetMapping("/account")
    public String account(Model m) {
        return "account";
    }

    @GetMapping("/auth-redirect")
    @PreAuthorize("isAuthenticated()")
    public String authRedirect(@RequestParam(name = "target", defaultValue = "/") String target) {
        // Da dieser Endpunkt geschützt ist, kommt man hier erst NACH dem Login an.
        // Jetzt leiten wir einfach zum eigentlichen Ziel weiter.
        return "redirect:" + target;
    }
}
