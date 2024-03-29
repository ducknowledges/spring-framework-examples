package com.github.ducknowledges.bookstore.command;

import com.github.ducknowledges.bookstore.domain.Genre;
import com.github.ducknowledges.bookstore.printformatter.PrintFormatter;
import com.github.ducknowledges.bookstore.service.GenreService;
import java.util.List;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class BookGenreCommand {

    private final GenreService genreService;
    private final PrintFormatter<Genre> printformatter;

    public BookGenreCommand(GenreService genreService,
                            PrintFormatter<Genre> printformatter) {
        this.genreService = genreService;
        this.printformatter = printformatter;
    }

    @ShellMethod(value = "Read all genres command", key = "read-genres")
    public String getGenres(@ShellOption long fromId,
                            @ShellOption long toId) {
        List<Genre> genres = genreService.getGenres(fromId, toId);
        return printformatter.format(genres);
    }

    @ShellMethod(value = "Read genre command", key = "read-genre")
    public String getAuthor(@ShellOption long genreId) {
        return genreService.getGenre(genreId)
            .map(printformatter::format)
            .orElse("Genre doesn't exist");
    }
}
