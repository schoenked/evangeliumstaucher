package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.ApiServices;
import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import de.evangeliumstaucher.repoDatatables.GameDatatablesRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
public class HarborController extends BaseController {
    private final QuizService quizService;
    private final UserService userService;
    private final HttpSession session;
    private final GameDatatablesRepository gameRepository;

    public HarborController(ApiServices apiServices, QuizService quizService, UserService userService, HttpSession session, GameDatatablesRepository gameRepository) {
        super(apiServices);
        this.quizService = quizService;
        this.userService = userService;
        this.session = session;
        this.gameRepository = gameRepository;
    }

    @ModelAttribute("playerModel")
    public PlayerModel PlayerModelAttribute(@AuthenticationPrincipal OAuth2User oidcUser) {
        return PlayerModel.from(userService.getByEMail(oidcUser.getAttribute("email")).get());
    }

    @GetMapping("/quiz/harbor/")
    public String get() {
        return "harbor.html";
    }
}
