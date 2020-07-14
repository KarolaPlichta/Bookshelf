package com.example.bookshelf.comment.internal;

import com.example.bookshelf.comment.boundary.CommentDTO;
import com.example.bookshelf.comment.boundary.CommentNotFoundException;
import com.example.bookshelf.comment.boundary.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CommentDTO> getFiveLatestCommentByBookId(UUID bookId) {
        var comments = commentRepository.findFirst5ByBook_IdOrderByCreationTime(bookId);
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO getById(UUID id) throws CommentNotFoundException {
        var comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment with id " + id + "not found"));
        return modelMapper.map(comment, CommentDTO.class);
    }


    @Override
    @Transactional
    public CommentDTO editContent(UUID id, String content) throws CommentNotFoundException {
        var comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment with id " + id + "not found"));
        comment.setContent(content);
        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public void removeComment(UUID id) {
        commentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void removeByBookId(UUID bookId) {
        commentRepository.deleteByBook_Id(bookId);
    }
}
