package com.example.bookshelf;

import com.example.bookshelf.book.internal.Book;
import com.example.bookshelf.book.internal.BookRepository;
import com.example.bookshelf.comment.internal.Comment;
import com.example.bookshelf.comment.internal.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Component
public class TestObject {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;


    Book existingBook() {
        Book book = new Book();
        book.setRate(4);
        book.setAuthor("Book author");
        book.setPages(204);
        book.setTitle("Title of the book");
        book.setISBNNumber("9783161484100");
        return bookRepository.save(book);
    }

    Comment existingComment() {

        Comment comment = new Comment();
        comment.setCreationTime(LocalDateTime.of(2020,2, 20, 12, 21));
        comment.setContent("Content of the comment");
        comment.setAuthor("Some author");
        comment.setBook(existingBook());
        return commentRepository.save(comment);
    }

    Book existingBookWithComments() {
        Book book = new Book();
        book.setRate(4);
        book.setAuthor("Book author");
        book.setPages(204);
        book.setTitle("Title of the book");
        book.setISBNNumber("9783161484100");
        book = bookRepository.save(book);
        Comment comment = new Comment();
        comment.setCreationTime(LocalDateTime.of(2020,2, 20, 12, 21));
        comment.setContent("Content of the comment");
        comment.setAuthor("Some author");
        comment.setBook(book);
        Comment comment1 = new Comment();
        comment1.setCreationTime(LocalDateTime.of(2020,2, 20, 12, 21));
        comment1.setContent("Content of the comment");
        comment1.setAuthor("Some author");
        comment1.setBook(book);
        Comment comment2 = new Comment();
        comment2.setCreationTime(LocalDateTime.of(2020,2, 20, 12, 21));
        comment2.setContent("Content of the comment");
        comment2.setAuthor("Some author");
        comment2.setBook(book);
        commentRepository.saveAll(Arrays.asList(comment, comment1, comment2));
        return book;
    }
}
