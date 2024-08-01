package com.example.AuthorService.repositories;

import com.example.AuthorService.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for managing Author entities.
 */
public interface IAuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Find authors by their name containing the specified keyword.
     *
     * @param keyword the keyword to search for in author names
     * @return a list of authors whose names contain the keyword
     */
    List<Author> findByAuthorNameContaining(String keyword);

    @Query(value = "CALL GetAuthorsWithBooks()", nativeQuery = true)
    List<Author> getAuthorsWithBooks();

}
