package com.example.entities;

import com.example.utils.Constants;
import com.example.utils.MessagesConstants;
import com.example.utils.TableConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = TableConstants.TABLE_BOOK_NAME)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TableConstants.COLUMN_ID)
    private Long id;

    @Column(name = TableConstants.COLUMN_TITLE, nullable = false)
    @NotBlank(message = MessagesConstants.BOOK_GENRE_MANDATORY)
    @Size(max = 255, message = MessagesConstants.BOOK_TITLE_SIZE)
    private String title;

    @Column(name = TableConstants.COLUMN_PUBLISHED_DATE)
    @NotNull(message = MessagesConstants.BOOK_PUBLISHEDDATE_MANDATORY)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    private Date publishedDate;

    @Column(name = TableConstants.COLUMN_GENRE)
    @NotBlank(message = MessagesConstants.BOOK_GENRE_MANDATORY)
    @Size(max = 255, message = MessagesConstants.BOOK_GENRE_SIZE)
    private String genre;

    @Column(name = TableConstants.COLUMN_DESCRIPTION, columnDefinition = "TEXT")
    private String description;

    private Long author;

    /**
     * Get the ID of the book.
     *
     * @return the ID of the book
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the title of the book.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the author of the book.
     *
     * @param author the author to set
     */
    public void setAuthor(Long author) {
        this.author = author;
    }

    /**
     * Get the author of the book.
     *
     * @return the author of the book
     */
    public Long getAuthor() {
        return author;
    }

    /**
     * Get the published date of the book.
     *
     * @return the published date of the book
     */
    public Date getPublishedDate() {
        return publishedDate;
    }

    /**
     * Set the published date of the book.
     *
     * @param publishedDate the published date to set
     */
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * Get the genre of the book.
     *
     * @return the genre of the book
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set the genre of the book.
     *
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Get the description of the book.
     *
     * @return the description of the book
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the book.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}

