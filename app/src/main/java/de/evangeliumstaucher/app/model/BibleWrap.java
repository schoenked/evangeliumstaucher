package de.evangeliumstaucher.app.model;

import de.evangeliumstaucher.repo.model.Bible;
import de.evangeliumstaucher.repo.model.BibleBook;
import de.evangeliumstaucher.repo.service.Library;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
public class BibleWrap {
    private final String id;
    private final Bible bible;
    @Getter(AccessLevel.PRIVATE)
    private List<BookWrap> books;

    public List<BookWrap> getBooks(Library library) {
        if (books == null) {
            List<? extends BibleBook> booklist = library.getBibleBooks(bible);

            books = booklist.stream()
                    .map(book ->
                            new BookWrap(book, this))
                    .collect(Collectors.toList());
        }
        return books;
    }
/*    @ToString.Exclude
    private final Bible bible;*/

    public BookWrap getLast(Library library) {
        return getBooks().get(getBooks().size() - 1);
    }

    public PassageTree getPassageTree(Library library) {
        List<PassageTree> passageTree = getBooks(library).stream()
                .filter(bookWrap -> bible.containsPassage(bookWrap.getBook().getId()))
                .map(bookWrap -> PassageTree.builder()
                        .id(bookWrap.getBook().getId())
                        .displ(bookWrap.getBook().getName())
                        .tree(bookWrap.getPassageTree(library))
                        .build())
                .collect(Collectors.toList());

        passageTree.addAll(0, getPassageTreeDivisions(library).stream()
                .filter(item -> passageTree.stream()
                        .noneMatch(p-> p.getId().equals(item.getId())))
                .toList()
        );
        return PassageTree.builder()
                .id("")
                .coll(false)
                .tree(passageTree)
                .build();
    }

    private Collection<? extends PassageTree> getPassageTreeDivisions(Library library) {
        return library.getDivisions(bible).stream()
                .map(s -> PassageTree
                        .builder()
                        .id(s.getRange())
                        .displ(s.getName())
                        //Activate Whole Bible
                        .actWhi(s.getRange().equals("Gen-Rev"))
                        .build())
                .toList();
    }
}
