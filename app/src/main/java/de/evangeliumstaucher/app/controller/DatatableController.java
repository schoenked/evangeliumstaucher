package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.DatatableService;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import de.evangeliumstaucher.entity.datatables.GameRowTrend;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DatatableController {

    private final DatatableService datatableService;
    private final UserService userService;

    @ModelAttribute("playerModel")
    public PlayerModel PlayerModelAttribute(@AuthenticationPrincipal OAuth2User oidcUser) {
        return PlayerModel.from(userService.getByEMail(oidcUser.getAttribute("email")).get());
    }

    @PostMapping(value = "/quiz/datatable/quizzes")
    public @ResponseBody DataTablesOutput<GameRowTrend> getQuizzes(@Valid @RequestBody DataTablesInput input, @ModelAttribute PlayerModel playerModel) {
        DataTablesOutput<GameRowTrend> out = datatableService.getTrendingGames(input, playerModel.getId());
        return out;
    }

}
