package de.evangeliumstaucher.app.controller;

import de.evangeliumstaucher.app.service.DatatableService;
import de.evangeliumstaucher.app.service.UserService;
import de.evangeliumstaucher.app.viewmodel.PlayerModel;
import de.evangeliumstaucher.entity.datatables.GameRowMyDives;
import de.evangeliumstaucher.entity.datatables.GameRowTrend;
import de.evangeliumstaucher.entity.datatables.QuestionScores;
import de.evangeliumstaucher.entity.datatables.QuizScores;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class DatatableController {

    private final DatatableService datatableService;
    private final UserService userService;

    @ModelAttribute("playerModel")
    @Nullable
    public PlayerModel PlayerModelAttribute(@AuthenticationPrincipal OAuth2User oidcUser) {
        return userService.getPlayerModel(oidcUser, userService);
    }

    @PostMapping(value = "/quiz/datatable/trending")
    public @ResponseBody DataTablesOutput<GameRowTrend> getQuizzes(@Valid @RequestBody DataTablesInput input, @ModelAttribute @Nullable PlayerModel playerModel) {
        DataTablesOutput<GameRowTrend> out = datatableService.getTrendingGames(input, playerModel != null ? playerModel.getId() : null);
        return out;
    }

    @PostMapping(value = "/quiz/datatable/myDives")
    public @ResponseBody DataTablesOutput<GameRowMyDives> getMyDives(@Valid @RequestBody DataTablesInput input, @ModelAttribute @Nullable PlayerModel playerModel) {
        DataTablesOutput<GameRowMyDives> out = datatableService.getMyDives(input, playerModel != null ? playerModel.getId() : null);
        return out;
    }

    @PostMapping(value = "/quiz/datatable/questionscore/{id}")
    public @ResponseBody DataTablesOutput<QuestionScores> getQuizzes(@Valid @RequestBody DataTablesInput input, @PathVariable @NotNull Long id, @ModelAttribute @Nullable PlayerModel playerModel) {
        DataTablesOutput<QuestionScores> out = datatableService.getQuestoinScores(input, id, playerModel != null ? playerModel.getId() : null);
        return out;
    }

    @PostMapping(value = "/quiz/datatable/quiz-scores/{quiz}")
    public @ResponseBody DataTablesOutput<QuizScores> getQuizScores(@Valid @RequestBody DataTablesInput input, @PathVariable UUID quiz, @ModelAttribute @Nullable PlayerModel playerModel) {
        DataTablesOutput<QuizScores> out = datatableService.getQuizScores(input, quiz, playerModel != null ? playerModel.getId() : null);
        return out;
    }
}
