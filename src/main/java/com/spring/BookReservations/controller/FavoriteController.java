package com.spring.BookReservations.controller;

import com.spring.BookReservations.model.Book;
import com.spring.BookReservations.model.Favorite;
import com.spring.BookReservations.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;

    @GetMapping("/book/favorite/{bookId}/{userId}")
    public String favoriteBook(@PathVariable("bookId") int bookId, @PathVariable("userId") int userId){
        if (!favoriteService.existsByBookIdAndUserId(bookId, userId)) {
            favoriteService.save(new Favorite(bookId, userId));
        }
        return "redirect:/dashboard";
    }
    @GetMapping("/account/unlike/{id}")
    public String removeFavorite(@PathVariable("id") int id){
        favoriteService.deleteByBookId(id);
        return "redirect:/account";
    }
}
