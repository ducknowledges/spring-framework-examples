package com.github.ducknowledges.bookstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.ducknowledges.bookstore.dao.BookCommentDao;
import com.github.ducknowledges.bookstore.dao.BookDao;
import com.github.ducknowledges.bookstore.domain.Book;
import com.github.ducknowledges.bookstore.domain.BookComment;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@DisplayName("Class BookServiceImpl")
class BookCommentServiceImplTest {

    @MockBean
    private BookCommentDao bookCommentDao;

    @MockBean
    private BookDao bookDao;

    @Autowired
    private BookCommentService commentService;

    private static BookComment comment;
    private static Book book;

    @BeforeAll
    static void setUpAll() {
        book = mock(Book.class);
        comment = new BookComment(1L, "content", book);
    }

    @Test
    @DisplayName("should create book comment")
    void shouldCreateBook() {
        commentService.createComment(comment);
        verify(bookCommentDao, times(1)).create(comment);
    }

    @Test
    @DisplayName("should return comment by id")
    void shouldReturnBookById() {
        long id = 1;
        Optional<BookComment> expectedComment = Optional.of(comment);
        when(bookCommentDao.findById(id)).thenReturn(expectedComment);
        Optional<BookComment> actualComment = commentService.getComment(id);
        assertThat(actualComment).isEqualTo(expectedComment);
        verify(bookCommentDao, times(1)).findById(id);
    }

    @Test
    @DisplayName("should return empty comment by id")
    void shouldReturnEmptyBookById() {
        long id = 1;
        Optional<BookComment> expectedComment = Optional.empty();
        when(bookCommentDao.findById(id)).thenReturn(expectedComment);
        Optional<BookComment> actualComment = commentService.getComment(id);
        assertThat(actualComment).isEqualTo(expectedComment);
        verify(bookCommentDao, times(1)).findById(id);
    }

    @Test
    @DisplayName("should return all comments")
    void shouldReturnAllComments() {
        long fromId = 1;
        long toId = 2;
        when(bookCommentDao.findAll(fromId, toId)).thenReturn(List.of(comment));
        List<BookComment> expectedComments = List.of(comment);
        List<BookComment> actualComments = commentService.getComments(fromId, toId);
        assertThat(actualComments).isEqualTo(expectedComments);
    }

    @Test
    @DisplayName("should return all comments by book id")
    void shouldReturnAllCommentsByBookId() {
        long bookId = 1L;
        when(book.getId()).thenReturn(bookId);
        when(bookCommentDao.findAllByBookId(bookId)).thenReturn(List.of(comment));
        when(bookDao.findById(bookId)).thenReturn(Optional.of(book));
        List<BookComment> expectedComments = List.of(comment);
        List<BookComment> actualComments = commentService.getCommentsByBookId(bookId);
        assertThat(actualComments).isEqualTo(expectedComments);
    }

    @Test
    @DisplayName("should return empty comments if book id not exist")
    void shouldReturnEmptyCommentsIfBookIdNotExist() {
        long bookId = 1L;
        when(bookDao.findById(bookId)).thenReturn(Optional.empty());
        List<BookComment> expectedComments = List.of();
        List<BookComment> actualComments = commentService.getCommentsByBookId(bookId);
        assertThat(actualComments).isEqualTo(expectedComments);
    }

    @Test
    @DisplayName("should update comment's content")
    void shouldUpdateComment() {
        commentService.updateContent(comment.getId(), comment.getContent());
        verify(bookCommentDao, times(1))
            .updateContent(comment.getId(), comment.getContent());
    }

    @Test
    @DisplayName("should delete comment")
    void shouldDeleteComment() {
        long id = 1;
        commentService.delete(id);
        verify(bookCommentDao, times(1)).deleteById(id);
    }
}