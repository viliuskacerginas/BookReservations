package com.spring.BookReservations.controller;

import com.spring.BookReservations.model.Book;
import com.spring.BookReservations.service.BookService;
import com.spring.BookReservations.service.CustomUserDetails;
import com.spring.BookReservations.service.FavoriteService;
import com.spring.BookReservations.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    GenreService genreService;
    @Autowired
    FavoriteService favoriteService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Book> bookList = bookService.getAll();
        model.addAttribute("bookList", bookList);
        return "dashboard";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<String> genres = genreService.findAllGenreNames();
        model.addAttribute("genres", genres);
        model.addAttribute("book", new Book());
        return "create";
    }

    @PostMapping("/create")
    public String createSubmit(@ModelAttribute Book book, Model model) {
        bookService.save(book);
        return "redirect:/dashboard";
    }
    @GetMapping("/book/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        Optional<Book> book = bookService.findById(id);
        List<String> genres = genreService.findAllGenreNames();
        model.addAttribute("genres", genres);
        model.addAttribute("book", book);
        return "edit-book";
    }
    @PostMapping("/book/edit")
    public String editBookSubmit(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/dashboard";
    }
    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") int id, Model model){
        bookService.deleteById(id);
        return "redirect:/dashboard";
    }
    @GetMapping("/dashboard/search")
    public String searchBooks(@RequestParam("query") String query, Model model) {
        List<Book> filteredBooks = bookService.findBooksByTitle(query);
        model.addAttribute("bookList", filteredBooks);
        return "dashboard";
    }
    @GetMapping("/book/reserve/{bookId}/{userId}")
    public String deleteBook(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId){
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (optionalBook.isPresent() && !optionalBook.get().isReserved()) {
            Book book = optionalBook.get();
            book.setReservationId(userId);
            book.setReserved(true);
            bookService.save(book);
        }
        return "redirect:/dashboard";
    }
    @GetMapping("/account")
    public String account(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        int userId = customUserDetails.getId();
        List<Book> reservedBooks = bookService.findBooksByReservationId(userId);
        List<Book> favoriteBooks = bookService.findByIdIn(favoriteService.findAllBookIdsByUserId(userId));
        model.addAttribute("reservedBooks", reservedBooks);
        model.addAttribute("favoriteBooks", favoriteBooks);
        return "account";
    }
    @GetMapping("/account/unreserve/{id}")
    public String removeReservation(@PathVariable("id") int id){
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setReservationId(0);
            book.setReserved(false);
            bookService.save(book);
        }
        return "redirect:/account";
    }

}
