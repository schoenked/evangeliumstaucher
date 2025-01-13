package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.model.BibleWrap;
import de.evangeliumstaucher.app.model.PassageTree;
import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import de.evangeliumstaucher.app.viewmodel.QuizModel;
import de.evangeliumstaucher.app.viewmodel.QuizSetupModel;
import de.evangeliumstaucher.repo.GameRepository;
import de.evangeliumstaucher.repo.service.Library;
import de.evangeliumstaucher.repoDatatables.TrendingGamesDatatablesRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@Valid
public class QuizEditorController extends BaseController {
    private final QuizService quizService;
    private final UserService userService;
    private final HttpSession session;
    private final GameRepository gameRepository;
    private final TrendingGamesDatatablesRepository trendingGamesDatatablesRepository;

    public QuizEditorController(Library library, QuizService quizService, UserService userService, HttpSession session, TrendingGamesDatatablesRepository trendingGamesDatatablesRepository, GameRepository gameRepository) {
        super(library);
        this.quizService = quizService;
        this.userService = userService;
        this.session = session;
        this.gameRepository = gameRepository;
        this.trendingGamesDatatablesRepository = trendingGamesDatatablesRepository;
    }

    @ModelAttribute("playerModel")
    public PlayerModel PlayerModelAttribute(@AuthenticationPrincipal OAuth2User oidcUser) {
        return PlayerModel.from(userService.getByEMail(oidcUser.getAttribute("email")).get());
    }

    @GetMapping("/quiz/create/")
    public String getQuizCreate(Model m) {
        return super.getBible(m);
    }

    /**
     * Creates new Quiz for the given bible
     *
     * @param bibleId
     * @param m
     * @return
     */
    @GetMapping("/quiz/create/{bibleId}/")
    public String createQuiz(@PathVariable String bibleId, Model m, @ModelAttribute PlayerModel playerModel) throws ServiceUnavailableException {
        try {
            BibleWrap bibleWrap = new BibleWrap(bibleId, library.getBible(bibleId));
            QuizSetupModel quizSetupModel = QuizSetupModel.from(bibleWrap, library);
            m.addAttribute("quizSetupModel", quizSetupModel);
            return "quiz_setup.html";
        } catch (Exception e) {
            log.error("Failed to create quiz", e);
            addWarning(m);
            handleUnavailable();
        }
        return null;
    }

    @PostMapping("/quiz/create/{bibleId}/submit")
    public String submitQuiz(@PathVariable String bibleId, @Valid @ModelAttribute QuizSetupModel quizSetupModel, @RequestParam Map<String, String> allRequestParams, Model m, @ModelAttribute PlayerModel playerModel) throws BadRequestException {
        String whitelist = allRequestParams.entrySet().stream()
                .filter(e -> e.getKey().startsWith("add_") && e.getValue().equals("on"))
                .map(e -> StringUtils.substring(e.getKey(), 4))
                .collect(Collectors.joining(","));
        String blacklist = allRequestParams.entrySet().stream()
                .filter(e -> e.getKey().startsWith("sub_") && e.getValue().equals("on"))
                .map(e -> StringUtils.substring(e.getKey(), 4))
                .collect(Collectors.joining(","));
        BibleWrap bible = new BibleWrap(bibleId, library.getBible(bibleId));

        quizSetupModel.setPassageTree(bible.getPassageTree(library));

        if (gameRepository.existsByName(quizSetupModel.getName())) {
            //check if unique name
            String message = "Es gibt bereits einen Tauchgang mit diesem Namen";
            m.addAttribute("quizSetupModel", quizSetupModel);
            m.addAttribute("error", message);
            return "quiz_setup.html";
        }

        QuizModel quizModel = quizService.createQuiz(bible, quizSetupModel, playerModel, whitelist, blacklist);
        if (quizModel == null) {
            String message = "Das Spiel konnte nicht erstellt werden.";
            m.addAttribute("quizSetupModel", quizSetupModel);
            m.addAttribute("error", message);
            return "quiz_setup.html";
        }
        return "redirect:" + quizModel.getUrl();
    }

    @PostMapping("/quiz/create/passagetree")
    public String renderPassageTree(@RequestBody PassageTree passageTree, Model m) {
        log.trace("renderPassageTree rendering " + passageTree.getId());
        m.addAttribute("passageTree", passageTree);
        return "fragment_passageTree.html";
    }

    public void addWarning(Model m) {
        m.addAttribute("warning", "An error occurred while creating the quiz. Please try again.");
    }

    private void handleUnavailable() throws ServiceUnavailableException {
        throw new ServiceUnavailableException("Service temporarily unavailable.");
    }
}

