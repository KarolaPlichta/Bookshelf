package com.example.bookshelf.comment.boundary;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommentDTO getComment(@PathVariable UUID id) throws CommentNotFoundException {
        return commentService.getById(id);
    }

    @PatchMapping("/{id}")
    public CommentDTO changeCommentsContent(@PathVariable UUID id,@Valid @RequestBody CommentContentRequest commentContentRequest) throws CommentNotFoundException {
        return commentService.editContent(id, commentContentRequest.getContent());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeComment(@PathVariable UUID id) {
        commentService.removeComment(id);
    }
}
