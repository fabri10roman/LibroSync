package com.demo.LibroSync.service;

import com.demo.LibroSync.dao.BookDao;
import com.demo.LibroSync.domain.Book;
import com.demo.LibroSync.dto.ResponsePayload;
import com.demo.LibroSync.exception.ConflictException;
import com.demo.LibroSync.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;

    public ResponsePayload create(Book book) throws BadRequestException {
        if (authorService.getAuthorById(book.getAuthorId()).isEmpty()) {
            throw new BadRequestException(String.format("Author with id %d not found", book.getAuthorId()));
        }
        if (bookDao.getByIsbn(book.getIsbn()).isPresent()) {
            throw new ConflictException(String.format("Book with isbn %s already exists", book.getIsbn()));
        }
        int result = bookDao.create(book);
        if (result != 0) {
            List<Object> data = List.of(book);
            return ResponsePayload.builder()
                    .message("Book created successfully")
                    .status(HttpStatus.CREATED.value())
                    .title(HttpStatus.CREATED.getReasonPhrase())
                    .data(data)
                    .build();
        }
        throw new IllegalStateException("Failed to create book");
    }
    public ResponsePayload getAll() {
        List<Book> books = bookDao.getAll();
        if (books.isEmpty()) {
            throw new EntityNotFoundException("No books found");
        }
        return ResponsePayload.builder()
                .message("Books retrieved successfully")
                .status(HttpStatus.OK.value())
                .title(HttpStatus.OK.getReasonPhrase())
                .data(List.of(books))
                .build();
    }
    public ResponsePayload getByIsbn(String isbn) {
        Book book = bookDao.getByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Book with isbn %s not found", isbn)));
        return ResponsePayload.builder()
                .message("Book retrieved successfully")
                .status(HttpStatus.OK.value())
                .title(HttpStatus.OK.getReasonPhrase())
                .data(List.of(book))
                .build();
    }
    public ResponsePayload deleteByIsbn(String isbn) {
        if (bookDao.getByIsbn(isbn).isEmpty()) {
            throw new EntityNotFoundException(String.format("Book with isbn %s not found", isbn));
        }
        int result = bookDao.deleteById(isbn);
        if (result == 1) {
            return ResponsePayload.builder()
                    .message("Book deleted successfully")
                    .status(HttpStatus.OK.value())
                    .title(HttpStatus.OK.getReasonPhrase())
                    .build();
        }
        throw new IllegalStateException("Failed to delete book");
    }
    public ResponsePayload partialUpdate(String isbn, Book bookDto) {
        Book book = bookDao.getByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Book with isbn %s not found", isbn)));
        if (bookDto.getIsbn() != null) {
            book.setIsbn(bookDto.getIsbn());
        }
        if (bookDto.getTitle() != null) {
            book.setTitle(bookDto.getTitle());
        }
        if (bookDto.getGenre() != null) {
            book.setGenre(bookDto.getGenre());
        }
        if (bookDto.getPublisher() != null) {
            book.setPublisher(bookDto.getPublisher());
        }
        if (bookDto.getYear() != null) {
            book.setYear(bookDto.getYear());
        }
        Integer authorId = bookDto.getAuthorId();
        if (authorService.getAuthorById(authorId).isEmpty()) {
            throw new EntityNotFoundException(String.format("Author with id %d not found", authorId));
        }
        if (authorId != null) {
            book.setAuthorId(bookDto.getAuthorId());
        }
        int result = bookDao.partialUpdate(isbn, book);
        if (result == 1) {
            return ResponsePayload.builder()
                    .message("Book updated successfully")
                    .status(HttpStatus.OK.value())
                    .title(HttpStatus.OK.getReasonPhrase())
                    .build();
        }
        throw new IllegalStateException("Failed to update book");
    }
    public ResponsePayload update (String isbn, Book book) {
        if (bookDao.getByIsbn(isbn).isEmpty()) {
            throw new EntityNotFoundException(String.format("Book with isbn %s not found", isbn));
        }
        if (bookDao.getByIsbn(book.getIsbn()).isPresent() && !book.getIsbn().equals(isbn)) {
            throw new ConflictException(String.format("Book with isbn %s already exists", book.getIsbn()));
        }
        Integer authorId = book.getAuthorId();
        if (authorService.getAuthorById(authorId).isEmpty()) {
            throw new EntityNotFoundException(String.format("Author with id %d not found", authorId));
        }
        int result = bookDao.update(isbn, book);
        if (result == 1) {
            return ResponsePayload.builder()
                    .message("Book updated successfully")
                    .status(HttpStatus.OK.value())
                    .title(HttpStatus.OK.getReasonPhrase())
                    .build();
        }
        throw new IllegalStateException("Failed to update book");
    }


}
