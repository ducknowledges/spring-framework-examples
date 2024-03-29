package com.github.ducknowledges.bookstore.dao;

import com.github.ducknowledges.bookstore.domain.BookComment;
import java.util.List;
import java.util.Optional;

public interface BookCommentDao {

    BookComment create(BookComment type);

    Optional<BookComment> readById(long id);

    List<BookComment> readAll(long fromId, long toId);

    BookComment update(BookComment comment);

    void delete(long id);
}
