package com.demo.LibroSync.controller;

import com.demo.LibroSync.domain.Book;
import com.demo.LibroSync.dto.ResponsePayload;
import com.demo.LibroSync.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponsePayload create(@RequestBody Book book) throws BadRequestException {
        return bookService.create(book);
    }
    @GetMapping
    public ResponsePayload getAll() {
        return bookService.getAll();
    }
    @GetMapping("/{isbn}")
    public ResponsePayload getByIsbn(@PathVariable String isbn) {
        return bookService.getByIsbn(isbn);
    }
    @DeleteMapping("/{isbn}")
    public ResponsePayload deleteByIsbn(@PathVariable String isbn) {
        return bookService.deleteByIsbn(isbn);
    }
    @PatchMapping("/{isbn}")
    public ResponsePayload partialUpdate(@RequestBody Book book, @PathVariable String isbn) {
        return bookService.partialUpdate(isbn,book);
    }
    @PutMapping("/{isbn}")
    public ResponsePayload update(@RequestBody Book book, @PathVariable String isbn) {
        return bookService.update(isbn,book);
    }

}
