package com.github.ducknowledges.bookstore.printformatter;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.ducknowledges.bookstore.domain.Author;
import com.github.ducknowledges.bookstore.domain.Book;
import com.github.ducknowledges.bookstore.domain.BookComment;
import com.github.ducknowledges.bookstore.domain.Genre;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("Class BookCommentPrintFormatter")
class BookCommentPrintFormatterTest {

    @Autowired
    BookCommentPrintFormatter commentFormatter;

    @Test
    @DisplayName("should format book comments to text")
    void shouldFormatBookCommentToText() {
        Book book = new Book(1L, "name",
            new Author(1L, "name"),
            new Genre(1L, "name"));
        BookComment comment1 = new BookComment(1L, "book comment 1", book);
        BookComment comment2 = new BookComment(2L, "book comment 2", book);

        StringBuilder stringBuilder = new StringBuilder("Comments:" + System.lineSeparator());
        stringBuilder
            .append(
                "id: " + comment1.getId()
                    + " content: " + comment1.getContent()
                    + " book: " + comment1.getBook().getName())
            .append(System.lineSeparator())
            .append(
                "id: " + comment2.getId()
                    + " content: " + comment2.getContent()
                    + " book: " + comment2.getBook().getName())
            .append(System.lineSeparator());

        String expected = stringBuilder.toString();
        String actual = commentFormatter.format(List.of(comment1, comment2));
        assertThat(actual).isEqualTo(expected);
    }
}