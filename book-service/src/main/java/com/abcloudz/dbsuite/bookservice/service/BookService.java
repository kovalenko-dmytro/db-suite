package com.abcloudz.dbsuite.bookservice.service;

import com.abcloudz.dbsuite.bookservice.dto.book.BookRequestDTO;
import com.abcloudz.dbsuite.bookservice.dto.book.BookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Locale;

public interface BookService {

    Page<BookResponseDTO> findAll(Pageable pageable);
    BookResponseDTO findById(long bookId, Locale locale);
    BookResponseDTO create(BookRequestDTO bookRequestDTO);
    BookResponseDTO update(long bookId, BookRequestDTO bookRequestDTO, Locale locale);
    void deleteById(long bookId);
}
