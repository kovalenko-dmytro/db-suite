package com.abcloudz.dbsuite.bookservice.util.mapper;

import com.abcloudz.dbsuite.bookservice.dto.book.BookResponseDTO;
import com.abcloudz.dbsuite.bookservice.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

    BookResponseDTO toResponseDTO(Book book);
}
