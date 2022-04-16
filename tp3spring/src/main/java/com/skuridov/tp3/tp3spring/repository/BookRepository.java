package com.skuridov.tp3.tp3spring.repository;

import com.skuridov.tp3.tp3spring.model.Document.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByTitleIsLikeIgnoreCase(String title);
    List<Book> findAllByAuthorIsLikeIgnoreCase(String author);
    List<Book> findAllByPublicationYear(int year);
    List<Book> findAllByGenre(String genre);
}
