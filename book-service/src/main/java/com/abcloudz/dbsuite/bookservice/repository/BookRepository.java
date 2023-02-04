package com.abcloudz.dbsuite.bookservice.repository;

import com.abcloudz.dbsuite.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
