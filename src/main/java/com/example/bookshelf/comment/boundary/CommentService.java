package com.example.bookshelf.comment.boundary;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    List<CommentDTO> getFiveLatestCommentByBookId(UUID bookId);

    CommentDTO getById(UUID id) throws CommentNotFoundException;

    CommentDTO editContent(UUID id, String content) throws CommentNotFoundException;

    void removeComment(UUID id);

    void removeByBookId(UUID bookId);

}
