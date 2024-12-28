package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.QuizService;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.app.viewmodel.DatatableColumn;
import de.evangeliumstaucher.app.viewmodel.DatatableViewModel;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
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

import java.util.List;

@Controller
@Slf4j
public class PierController extends BaseController {
    private final QuizService quizService;
    private final UserService userService;
    private final HttpSession session;

    public PierController(Library library, QuizService quizService, UserService userService, HttpSession session, TrendingGamesDatatablesRepository gameRepository) {
        super(library);
        this.quizService = quizService;
        this.userService = userService;
        this.session = session;
    }

    @ModelAttribute("playerModel")
    public PlayerModel PlayerModelAttribute(@AuthenticationPrincipal OAuth2User oidcUser) {
        return PlayerModel.from(userService.getByEMail(oidcUser.getAttribute("email")).get());
    }

    @GetMapping("/quiz/pier/")
    public String get(Model m) {
        DatatableViewModel modelTrending = new DatatableViewModel();
        List<DatatableColumn> columns = List.of(
                new DatatableColumn("Pos.", "position", "num").withOrder(DatatableColumn.Order.ASC),
                new DatatableColumn("Name", "name"),
                new DatatableColumn("von", "creator"),
                new DatatableColumn("Datum", "createdAt", "date"),
                new DatatableColumn("Spieler", "playerCount","num")
        );
        modelTrending.setColumns(columns);
        modelTrending.setUrl("/quiz/datatable/trending");
        m.addAttribute("modelTrending", modelTrending);

        DatatableViewModel modelMyDives = new DatatableViewModel();
        List<DatatableColumn> columnsMyDives = List.of(
                new DatatableColumn("Name", "name"),
                new DatatableColumn("Startzeit", "startedAt", "datetime").withOrder(DatatableColumn.Order.DESC),
                new DatatableColumn("Fortschritt", "progress","num-fmt" )
        );
        modelMyDives.setColumns(columnsMyDives);
        modelMyDives.setUrl("/quiz/datatable/myDives");
        m.addAttribute("modelMyDives", modelMyDives);

        return "pier.html";
    }
}
