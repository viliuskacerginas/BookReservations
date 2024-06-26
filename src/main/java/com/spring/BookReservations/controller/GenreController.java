package com.spring.BookReservations.controller;

import com.spring.BookReservations.model.Genre;
import com.spring.BookReservations.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class GenreController {
    @Autowired
    GenreService genreService;
    @GetMapping("/genre")
    public String genre(Model model) {
        List<Genre> genreList = genreService.findAll();
        model.addAttribute("genreList", genreList);
        model.addAttribute("genre", new Genre());
        return "genre";
    }

    @PostMapping("/genre")
    public String genreSubmit(@ModelAttribute Genre genre, Model model) {
        genreService.save(genre);
        return "redirect:/genre";
    }
    @GetMapping("/genre/delete/{id}")
    public String deleteGenre(@PathVariable("id") int id, Model model){
        genreService.deleteById(id);
        return "redirect:/genre";
    }
    @GetMapping("/genre/edit/{id}")
    public String editGenre(@PathVariable("id") int id, Model model) {
        Optional<Genre> genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        return "edit-genre";
    }
    @PostMapping("/genre/edit")
    public String editGenreSubmit(@ModelAttribute Genre genre) {
        genreService.save(genre);
        return "redirect:/genre";
    }
}
