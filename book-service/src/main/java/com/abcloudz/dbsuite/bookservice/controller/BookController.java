package com.abcloudz.dbsuite.bookservice.controller;

import com.abcloudz.dbsuite.bookservice.dto.book.BookRequestDTO;
import com.abcloudz.dbsuite.bookservice.dto.book.BookResponseDTO;
import com.abcloudz.dbsuite.bookservice.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@Tag(name = "Book Service REST API")
@RestController
@RequestMapping(value = "/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(bookService.findAll(pageable));
    }

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable("bookId") long bookId,
                                                    @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok().body(bookService.findById(bookId, locale));
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> create(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookRequestDTO));
    }

    @PutMapping(value = "/{bookId}")
    public ResponseEntity<BookResponseDTO> update(@PathVariable("bookId") long bookId,
                                                  @Valid @RequestBody BookRequestDTO bookRequestDTO,
                                                  @RequestParam(value = "locale", required = false, defaultValue = "en") Locale locale) {
        return ResponseEntity.ok().body(bookService.update(bookId, bookRequestDTO, locale));
    }

    @DeleteMapping(value = "/{bookId}")
    public ResponseEntity<Void> findById(@PathVariable("bookId") long bookId) {
        bookService.deleteById(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
