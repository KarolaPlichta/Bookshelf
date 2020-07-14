package com.example.bookshelf.book.boundary;

import com.example.bookshelf.PaginatedResponse;
import com.example.bookshelf.comment.boundary.CommentDTO;

import java.util.UUID;

public interface BookService {

    BookDTO saveBook(BookDTO bookDTO);

    BookWithCommentDTO getBook(UUID id) throws BookNotFoundException;

    PaginatedResponse<BookWithCommentDTO> getAllBooksPaginated(int pageNumber, int elementsSize);

    BookDTO updateBook(UUID id, BookDTO bookDTO) throws BookNotFoundException;

    void removeBook(UUID id);

}
