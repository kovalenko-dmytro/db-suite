package com.abcloudz.dbsuite.bookservice.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookRequestDTO {

    @NotBlank
    private String author;
    @NotBlank
    private String title;
}
