package com.github.ducknowledges.bookstore.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Class BookComment")
class BookCommentTest {

    @Test
    @DisplayName("correctly created by the constructor")
    void shouldHasCorrectConstructor() {
        Book book = new Book();
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
        Book book = new Book();
        BookComment comment  = new BookComment("content", book);
        assertAll(
            () -> assertThat(comment.getContent()).isEqualTo("content"),
            () -> assertThat(comment.getBook()).isEqualTo(book)
        );
    }

    @Test
    @DisplayName("should has correct equality")
    void shouldHasCorrectEquality() {
        Book book = new Book();
        assertThat(new BookComment(1L, "content", book))
            .isEqualTo(new BookComment(1L, "content", book));
    }

    @Test
    @DisplayName("should has correct setters")
    void shouldHasCorrectSetters() {
        Author author = new Author(1L, "name");
        Genre genre = new Genre(1L, "name");
        Book book = new Book(1L, "name", author, genre);

        BookComment comment = new BookComment();
        comment.setId(1L);
        comment.setContent("content");
        comment.setBook(book);

        assertAll(
            () -> assertThat(comment.getId()).isEqualTo(1L),
            () -> assertThat(comment.getContent()).isEqualTo("content"),
            () -> assertThat(comment.getBook()).isEqualTo(book)
        );
    }
}