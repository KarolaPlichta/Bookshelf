package com.example.bookshelf.book.boundary;

import java.util.List;

public interface BookService {

    BookDTO saveBook(BookDTO bookDTO);

    BookDTO getBook(Long id) throws BookNotFoundException;

    List<BookDTO> getAllBooks();

    BookDTO updateBook(Long id, BookDTO bookDTO) throws BookNotFoundException;

    void removeBook(Long id);


}
