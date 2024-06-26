package com.spring.BookReservations.service;

import com.spring.BookReservations.model.Book;
import com.spring.BookReservations.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
    @Override
    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findBooksByTitle(String query) {
        return bookRepository.findByTitleContainingIgnoreCase(query);
    }

    @Override
    public List<Book> findBooksByReservationId(int id) {
        return bookRepository.findByReservationId(id);
    }

    @Override
    public List<Book> findByIdIn(List<Integer> ids) {
        return bookRepository.findByIdIn(ids);
    }
}
