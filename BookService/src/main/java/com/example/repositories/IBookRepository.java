package com.example.repositories;


import com.example.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing Book entities.
 */
public interface IBookRepository extends JpaRepository<Book, Long> {

    /**
     * Find a book by its title.
     *
     * @param title the title of the book to search for
     * @return an Optional containing the book with the specified title, or empty if not found
     */
    Optional<Book> findByTitle(String title);
}
