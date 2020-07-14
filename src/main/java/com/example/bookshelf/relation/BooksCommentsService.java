package com.example.bookshelf.relation;

import com.example.bookshelf.PaginatedResponse;
import com.example.bookshelf.book.boundary.BookNotFoundException;
import com.example.bookshelf.comment.boundary.CommentDTO;

import java.util.UUID;

public interface BooksCommentsService {

    PaginatedResponse<CommentDTO> getCommentsByBookId(UUID bookId, int pageNumber, int elementsOnSite);

    CommentDTO addCommentToTheBook(UUID bookId, CommentDTO commentDTO) throws BookNotFoundException;

}
