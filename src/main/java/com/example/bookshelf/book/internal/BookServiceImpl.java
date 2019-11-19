package com.example.bookshelf.book.internal;

import com.example.bookshelf.book.boundary.BookDTO;
import com.example.bookshelf.book.boundary.BookNotFoundException;
import com.example.bookshelf.book.boundary.BookService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private BookRepository bookRepository;
    private ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public BookDTO saveBook(BookDTO bookDTO) {
        var book = modelMapper.map(bookDTO, Book.class);
        book = bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public BookDTO getBook(Long id) throws BookNotFoundException {
        var book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        var books = bookRepository.findAll();
        return books.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookDTO updateBook(Long id, BookDTO bookDTO) throws BookNotFoundException {
        var book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        updateBook(book, bookDTO);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public void removeBook(Long id) {
        bookRepository.deleteById(id);
    }

    private void updateBook(Book book, BookDTO bookDTO) {
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setISBNNumber(bookDTO.getISBNNumber());
        book.setPages(bookDTO.getPages());
        book.setRate(bookDTO.getRate());
    }
}
