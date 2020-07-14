package com.example.bookshelf.relation;

import com.example.bookshelf.PaginatedResponse;
import com.example.bookshelf.book.boundary.BookNotFoundException;
import com.example.bookshelf.book.internal.BookRepository;
import com.example.bookshelf.comment.boundary.CommentDTO;
import com.example.bookshelf.comment.internal.Comment;
import com.example.bookshelf.comment.internal.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BooksCommentsServiceImpl implements BooksCommentsService {

    private BookRepository bookRepository;
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public BooksCommentsServiceImpl(BookRepository bookRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PaginatedResponse<CommentDTO> getCommentsByBookId(UUID bookId, int pageNumber, int elementsOnSite) {
        var commentsPage = commentRepository.findAllByBook_Id(bookId, PageRequest.of(pageNumber, elementsOnSite));
        var comments = commentsPage.get()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
        return new PaginatedResponse<>(commentsPage.getTotalPages(), commentsPage.getTotalElements(), commentsPage.getNumber(), comments);

    }

    @Override
    @Transactional
    public CommentDTO addCommentToTheBook(UUID bookId, CommentDTO commentDTO) throws BookNotFoundException {
        var comment = modelMapper.map(commentDTO, Comment.class);
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException();
        }
        var book = bookRepository.getOne(bookId);
        comment.setBook(book);
        comment.setCreationTime(LocalDateTime.now());
        comment = commentRepository.save(comment);
        return modelMapper.map(comment, CommentDTO.class);
    }
}
