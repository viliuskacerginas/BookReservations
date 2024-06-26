package com.spring.BookReservations.repository;

import com.spring.BookReservations.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByTitleContainingIgnoreCase(String query);
    List<Book> findByReservationId(int id);
    List<Book> findByIdIn(List<Integer> ids);
}