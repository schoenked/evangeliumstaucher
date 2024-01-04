package com.devrezaur.main.service;

import com.devrezaur.main.controller.Part;
import com.devrezaur.main.model.Question;
import com.devrezaur.main.model.QuestionForm;
import com.devrezaur.main.model.Result;
import com.devrezaur.main.repository.QuestionRepo;
import com.devrezaur.main.repository.ResultRepo;
import com.devrezaur.main.utils.ListUtils;
import com.devrezaur.main.viewmodel.QuizModel;
import de.evangeliumstaucher.invoker.ApiException;
import de.evangeliumstaucher.model.Book;
import de.evangeliumstaucher.model.ChapterSummary;
import de.evangeliumstaucher.model.Passage;
import de.evangeliumstaucher.model.VerseSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final BibleService bibleService;
    private final BookService bookService;
    private final ChaptersService chaptersService;
    private final VersesService versesService;
    private final PassageService passageService;
    private final QuestionForm qForm;
    private final QuestionRepo qRepo;
    private final ResultRepo rRepo;

    public QuestionForm getQuestions() {
        List<Question> allQues = qRepo.findAll();
        List<Question> qList = new ArrayList<Question>();

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }

        qForm.setQuestions(qList);

        return qForm;
    }

    public int getResult(QuestionForm qForm) {
        int correct = 0;

        for (Question q : qForm.getQuestions())
            if (q.getAns() == q.getChose()) correct++;

        return correct;
    }

    public void saveScore(Result result) {
        Result saveResult = new Result();
        saveResult.setUsername(result.getUsername());
        saveResult.setTotalCorrect(result.getTotalCorrect());
        rRepo.save(saveResult);
    }

    public List<Result> getTopScore() {
        List<Result> sList = rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));

        return sList;
    }

    public QuizModel getQuiz(String bibleId) throws ApiException {

        QuizModel quizModel = null;
        Book book = ListUtils.randomItem(bookService.getBibleBooks(bibleId));
        ChapterSummary chapter = ListUtils.randomItem(book.getChapters());
        List<VerseSummary> verses = versesService.getVerses(bibleId, chapter.getId());
        VerseSummary verse = ListUtils.randomItem(verses);
        quizModel = QuizModel.builder().verse(verse).build();
        return quizModel;
    }

    public Passage getPassage(QuizModel currentQuiz, String bibleId, Part part) throws ApiException {
        String passageId = currentQuiz.getVerse().getId();
        switch (part) {
            case pre -> passageId = "GEN.1.3";
            case post -> passageId = "GEN.1.4";
        }
        return passageService.getPassage(bibleId, passageId, null, false, false, false, false, false, null, false);
    }
}
