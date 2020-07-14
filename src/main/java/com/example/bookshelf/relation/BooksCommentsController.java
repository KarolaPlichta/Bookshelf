package com.example.bookshelf.relation;

import com.example.bookshelf.PaginatedResponse;
import com.example.bookshelf.book.boundary.BookNotFoundException;
import com.example.bookshelf.comment.boundary.CommentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.UUID;

@RestController
@RequestMapping("/book/{bookId}/comment")
public class BooksCommentsController {

    private BooksCommentsService booksCommentsService;

    public BooksCommentsController(BooksCommentsService booksCommentsService) {
        this.booksCommentsService = booksCommentsService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PaginatedResponse<CommentDTO> getComments(@PathVariable UUID bookId, @RequestParam("pageNumber") int pageNumber, @Valid @Size(max = 100) @RequestParam("elementsOnSite") int elementsOnSite) {
        return booksCommentsService.getCommentsByBookId(bookId, pageNumber, elementsOnSite);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO addCommentToBook(@PathVariable UUID bookId, @RequestBody CommentDTO commentDTO) throws BookNotFoundException {
        return booksCommentsService.addCommentToTheBook(bookId, commentDTO);
    }

}
