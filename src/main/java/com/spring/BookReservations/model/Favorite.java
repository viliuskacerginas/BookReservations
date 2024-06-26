package com.spring.BookReservations.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "book_id")
    private int bookId;
    @Column(name = "user_id")
    private int userId;

    public Favorite() {
    }

    public Favorite(int id, int bookId, int userId) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }

    public Favorite(int bookId, int userId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBook_id(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                '}';
    }
}
