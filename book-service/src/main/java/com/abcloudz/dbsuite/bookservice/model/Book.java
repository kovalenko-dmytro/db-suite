package com.abcloudz.dbsuite.bookservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book", uniqueConstraints = {@UniqueConstraint(columnNames = {"author", "title"})})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Book {

    @Id
    @Column(name = "book_", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seqGen")
    @SequenceGenerator(name = "book_seqGen", sequenceName = "book_book__seq", allocationSize = 1)
    private Long bookId;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "title", nullable = false)
    private String title;
}
