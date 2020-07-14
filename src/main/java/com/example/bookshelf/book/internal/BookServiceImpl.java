package com.example.bookshelf.book.internal;

import com.example.bookshelf.PaginatedResponse;
import com.example.bookshelf.book.boundary.BookDTO;
import com.example.bookshelf.book.boundary.BookNotFoundException;
import com.example.bookshelf.book.boundary.BookService;
import com.example.bookshelf.book.boundary.BookWithCommentDTO;
import com.example.bookshelf.comment.boundary.CommentDTO;
import com.example.bookshelf.comment.boundary.CommentService;
import com.example.bookshelf.comment.internal.Comment;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private BookRepository bookRepository;
    private CommentService commentService;
    private ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, CommentService commentService, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.commentService = commentService;
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
    public BookWithCommentDTO getBook(UUID id) throws BookNotFoundException {
        var book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        var bookDTO = modelMapper.map(book, BookWithCommentDTO.class);
        var comments = commentService.getFiveLatestCommentByBookId(book.getId());
        bookDTO.setComments(comments);
        return bookDTO;
    }

    @Override
    public PaginatedResponse<BookWithCommentDTO> getAllBooksPaginated(int pageNumber, int elementsSize) {
        var booksPage = bookRepository.findAll(PageRequest.of(pageNumber, elementsSize));
        var bookDTOList = booksPage.get()
                .map(book -> {
                    var bookDTO = modelMapper.map(book, BookWithCommentDTO.class);
                    var comments = commentService.getFiveLatestCommentByBookId(book.getId());
                    bookDTO.setComments(comments);
                    return bookDTO;
                })
                .collect(Collectors.toList());
        return new PaginatedResponse<>(booksPage.getTotalPages(), booksPage.getTotalElements(), booksPage.getNumber(), bookDTOList);
    }

    @Override
    @Transactional
    public BookDTO updateBook(UUID id, BookDTO bookDTO) throws BookNotFoundException {
        var book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        updateBook(book, bookDTO);
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    @Transactional
    public void removeBook(UUID id) {
        commentService.removeByBookId(id);
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
