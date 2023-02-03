package com.github.ducknowledges.bookstore.dao;

import com.github.ducknowledges.bookstore.dao.crud.CreateDao;
import com.github.ducknowledges.bookstore.dao.crud.DeleteDao;
import com.github.ducknowledges.bookstore.dao.crud.ReadDao;
import com.github.ducknowledges.bookstore.dao.crud.UpdateDao;
import com.github.ducknowledges.bookstore.domain.BookComment;
import java.util.List;

public interface BookCommentDao extends CreateDao<BookComment>,
                                        ReadDao<BookComment>,
                                        UpdateDao<BookComment>,
                                        DeleteDao {

    List<BookComment> readAllByBookId(long bookId);

    void deleteAllByBookId(long bookId);

}
