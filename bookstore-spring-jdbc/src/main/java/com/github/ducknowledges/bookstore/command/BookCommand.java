package com.github.ducknowledges.bookstore.command;

import com.github.ducknowledges.bookstore.domain.Author;
import com.github.ducknowledges.bookstore.domain.Book;
import com.github.ducknowledges.bookstore.domain.Genre;
import com.github.ducknowledges.bookstore.printformatter.PrintFormatter;
import com.github.ducknowledges.bookstore.service.AuthorService;
import com.github.ducknowledges.bookstore.service.BookService;
import com.github.ducknowledges.bookstore.service.GenreService;
import java.util.List;
import java.util.Optional;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookCommand {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final PrintFormatter<Book> printFormatter;

    public BookCommand(BookService bookService,
                       AuthorService authorService,
                       GenreService genreService,
                       PrintFormatter<Book> printFormatter) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.printFormatter = printFormatter;
    }

    @ShellMethod(value = "Create book command", key = "create-book")
    public String createBook(@ShellOption String name,
                         @ShellOption int authorId,
                         @ShellOption int genreId) {
        Optional<Author> author = authorService.getAuthor(authorId);
        if (author.isEmpty()) {
            return  "Author does not exist";
        }
        Optional<Genre> genre = genreService.getGenre(genreId);
        if (genre.isEmpty()) {
            return "Genre does not exist";
        }
        Book book = new Book(name, author.get(), genre.get());
        bookService.createBook(book);
        return "Book was created";
    }

    @ShellMethod(value = "Read book command", key = "read-book")
    public String getBook(@ShellOption long bookId) {
        return bookService.getBook(bookId)
            .map(printFormatter::format)
            .orElse("Book doesn't exist");
    }

    @ShellMethod(value = "Read all books command", key = "read-books")
    public String getBooks(long fromId, long toId) {
        List<Book> books = bookService.getBooks(fromId, toId);
        return printFormatter.format(books);
    }

    @ShellMethod(value = "Update book command", key = "update-book")
    public String update(@ShellOption long bookId,
                         @ShellOption String name,
                         @ShellOption int authorId,
                         @ShellOption int genreId) {
        if (!bookService.bookExists(bookId)) {
            return "Book doesn't exist";
        }
        Optional<Author> author = authorService.getAuthor(authorId);
        if (author.isEmpty()) {
            return "Author does not exist";
        }
        Optional<Genre> genre = genreService.getGenre(genreId);
        if (genre.isEmpty()) {
            return "Genre does not exist";
        }
        Book book = new Book(bookId, name, author.get(), genre.get());
        bookService.update(book);
        return "Book was updated";
    }

    @ShellMethod(value = "Delete book with comments command", key = "delete-book")
    public String delete(@ShellOption int bookId) {
        bookService.deleteWithChildComments(bookId);
        return "Book was deleted";
    }
}
