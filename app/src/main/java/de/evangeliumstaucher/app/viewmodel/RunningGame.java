package de.evangeliumstaucher.app.viewmodel;

import de.evangeliumstaucher.app.service.ApiServices;
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

    public RunningQuestion createRunningQuestion(ApiServices apiServices) throws ApiException {
        RunningQuestion created = new RunningQuestion(this);
        created.setBooks(getBooks(apiServices));
        questions.add(created);
        return created;
    }

    private List<BookModel> getBooks(ApiServices apiServices) throws ApiException {
        books = quizModel.getBible(apiServices.getBibleService()).getBooks(apiServices.getBookService()).stream()
                .map(bookWrap -> {
                    try {
                        return BookModel.from(bookWrap.getBook(), apiServices.getVersesService());
                    } catch (ApiException e) {
                        throw new RuntimeException(e);
                    }
                })
                .peek(book -> book.setPrefixVerses("select"))
                .collect(Collectors.toList());
        if (books == null) {
        }
        return books;
    }
}
