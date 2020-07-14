package com.example.bookshelf.book.boundary;

import com.example.bookshelf.PaginatedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PaginatedResponse<BookWithCommentDTO> getBooks(@RequestParam("pageNumber") int pageNumber, @RequestParam("elementsOnSize") int elementsSize) {
        return bookService.getAllBooksPaginated(pageNumber, elementsSize);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BookWithCommentDTO getBook(@PathVariable UUID id) throws BookNotFoundException {
        return bookService.getBook(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BookDTO create(@Valid @RequestBody BookDTO bookDTO) {
        return bookService.saveBook(bookDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BookDTO updateBook(@PathVariable UUID id, @Valid @RequestBody BookDTO bookDTO) throws BookNotFoundException {
        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable UUID id) {
        bookService.removeBook(id);
    }
}
