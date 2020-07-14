package com.example.bookshelf.comment.internal;


import com.example.bookshelf.book.internal.Book;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment", indexes = {
        @Index(name = "idx_comment_book_id", columnList = "book_id"),
        @Index(name = "idx_comment_creation_time", columnList = "creationTime")
})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Size(max = 255)
    @NotBlank
    private String author;
    @Size(max = 1000)
    @Column(length = 1000)
    @NotBlank
    private String content;
    @NotNull
    private LocalDateTime creationTime;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Book book;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
