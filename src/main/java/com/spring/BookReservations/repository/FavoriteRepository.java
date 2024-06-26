package com.spring.BookReservations.repository;

import com.spring.BookReservations.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    boolean existsByBookIdAndUserId(int bookId, int userId);

    @Query("SELECT f.bookId FROM Favorite f WHERE f.userId = :userId")
    List<Integer> findAllBookIdsByUserId(@Param("userId") int userId);

    void deleteByBookId(int id);
}
