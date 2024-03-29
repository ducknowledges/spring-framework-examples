package com.github.ducknowledges.bookstore.service;

import com.github.ducknowledges.bookstore.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    void createBook(Book book);

    boolean bookExists(long id);

    Optional<Book> getBook(long id);

    List<Book> getBooks(long fromId, long toId);

    void update(Book book);

    void deleteWithChildComments(long id);
}
