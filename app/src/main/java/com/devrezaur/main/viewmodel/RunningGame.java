package com.devrezaur.main.viewmodel;

import com.devrezaur.main.service.VersesService;
import de.evangeliumstaucher.invoker.ApiException;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RunningGame {
    private List<RunningQuestion> questions = new LinkedList<>();
    private QuizModel quizModel;
    private List<BookModel> books;

    public RunningQuestion createRunningQuestion(VersesService versesService) {
        RunningQuestion created = new RunningQuestion(this);
        created.setBooks(getBooks(versesService));
        questions.add(created);
        return created;
    }

    private List<BookModel> getBooks(VersesService versesService) {
        if (books == null) {
            books = quizModel.getBible().getBooks().stream()
                    .map(bookWrap -> {
                        try {
                            return BookModel.from(bookWrap.getBook(), versesService);
                        } catch (ApiException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .peek(book -> book.setPrefixVerses("select"))
                    .collect(Collectors.toList());
        }
        return books;
    }
}
