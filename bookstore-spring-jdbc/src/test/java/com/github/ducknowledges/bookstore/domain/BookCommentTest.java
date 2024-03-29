package com.github.ducknowledges.bookstore.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Class BookComment")
class BookCommentTest {

    private static Book book;

    @BeforeAll
    static void setBook() {
        book = new Book(1L, "name",
            new Author(1L, "name"),
            new Genre(1L, "name"));
    }

    @Test
    @DisplayName("correctly created by the constructor")
    void shouldHasCorrectConstructor() {
        BookComment comment  = new BookComment(1L, "content", book);
        assertAll(
            () -> assertThat(comment.getId()).isEqualTo(1),
            () -> assertThat(comment.getContent()).isEqualTo("content"),
            () -> assertThat(comment.getBook()).isEqualTo(book)
        );
    }

    @Test
    @DisplayName("correctly created by the constructor without id arg")
    void shouldHasCorrectConstructorWithoutId() {
        BookComment comment  = new BookComment("content", book);
        assertAll(
            () -> assertThat(comment.getContent()).isEqualTo("content"),
            () -> assertThat(comment.getBook()).isEqualTo(book)
        );
    }

    @Test
    @DisplayName("should has correct equality")
    void shouldHasCorrectEquality() {
        assertThat(new BookComment(1L, "content", book))
            .isEqualTo(new BookComment(1L, "content", book));
    }
}