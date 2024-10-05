package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import de.evangeliumstaucher.app.viewmodel.QuizSetupModel;
import de.evangeliumstaucher.repo.service.Library;
import de.evangeliumstaucher.repoDatatables.TrendingGamesDatatablesRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.ServiceUnavailableException;

@Controller
@Slf4j
public class QuizEditorController extends BaseController {
    private final QuizService quizService;
    private final UserService userService;
    private final HttpSession session;

    public QuizEditorController(Library library, QuizService quizService, UserService userService, HttpSession session, TrendingGamesDatatablesRepository gameRepository) {
        super(library);
        this.quizService = quizService;
        this.userService = userService;
        this.session = session;
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
            QuizSetupModel quizSetupModel = new QuizSetupModel();
            m.addAttribute("quizSetupModel", quizSetupModel);
            return "quiz_setup.html";
        } catch (Exception e) {
            log.error("Failed to create quiz", e);
            addWarning(m);
            handleUnavailable();
        }
        return null;
    }

    public void addWarning(Model m) {
        m.addAttribute("warning", "An error occurred while creating the quiz. Please try again.");
    }

    private void handleUnavailable() throws ServiceUnavailableException {
        throw new ServiceUnavailableException("Service temporarily unavailable.");
    }

    @PostMapping("/quiz/create/submit")
    public RedirectView submitQuiz(@ModelAttribute QuizSetupModel quizSetupModel, Model m) {
        // Process the quiz creation logic here
        // e.g., save quizModel to the database

        return new RedirectView("/quiz/success"); // Redirect to a success page or appropriate action
    }
}

