package com.abcloudz.dbsuite.bookservice.service.impl;

import com.abcloudz.dbsuite.bookservice.common.Entity;
import com.abcloudz.dbsuite.bookservice.common.message.Error;
import com.abcloudz.dbsuite.bookservice.dto.book.BookRequestDTO;
import com.abcloudz.dbsuite.bookservice.dto.book.BookResponseDTO;
import com.abcloudz.dbsuite.bookservice.exception.EntityNotFoundException;
import com.abcloudz.dbsuite.bookservice.model.Book;
import com.abcloudz.dbsuite.bookservice.repository.BookRepository;
import com.abcloudz.dbsuite.bookservice.service.BookService;
import com.abcloudz.dbsuite.bookservice.util.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final MessageSource messageSource;
    private final BookMapper bookMapper;

    @Override
    public Page<BookResponseDTO> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::toResponseDTO);
    }

    @Override
    public BookResponseDTO findById(long bookId, Locale locale) {
        return bookMapper.toResponseDTO(getBookById(bookId, locale));
    }

    @Override
    public BookResponseDTO create(BookRequestDTO bookRequestDTO) {
        Book book = Book.builder()
            .title(bookRequestDTO.getTitle())
            .author(bookRequestDTO.getAuthor())
            .addedAt(LocalDateTime.now())
            .build();
        return bookMapper.toResponseDTO(bookRepository.save(book));
    }

    @Override
    public BookResponseDTO update(long bookId, BookRequestDTO bookRequestDTO, Locale locale) {
        Book book = getBookById(bookId, locale);
        book.setTitle(bookRequestDTO.getTitle());
        book.setAuthor(bookRequestDTO.getAuthor());
        return bookMapper.toResponseDTO(bookRepository.save(book));
    }

    @Override
    public void deleteById(long bookId) {
        bookRepository.deleteById(bookId);
    }

    private Book getBookById(long bookId, Locale locale) {
        Object[] params = new Object[]{Entity.BOOK.getName(), Entity.BookField.BOOK_ID.getFieldName(), bookId};
        return bookRepository.findById(bookId)
            .orElseThrow(() ->
                new EntityNotFoundException(messageSource.getMessage(Error.ENTITY_NOT_FOUND.getKey(), params, locale)));
    }
}
