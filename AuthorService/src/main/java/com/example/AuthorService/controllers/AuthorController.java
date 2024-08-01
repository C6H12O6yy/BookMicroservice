package com.example.AuthorService.controllers;


import com.example.AuthorService.dto.request.AuthorRequest;
import com.example.AuthorService.dto.response.AuthorResponse;
import com.example.AuthorService.services.IAuthorService;
import com.example.AuthorService.utils.Constants;
import com.example.AuthorService.utils.MessagesConstants;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller class for handling operations related to authors.
 * All endpoints in this controller are mapped under "/authors".
 */
@RestController
@RequestMapping("/authors")
@Api(tags = "Author Management")
public class AuthorController {

        @Autowired
        private IAuthorService authorService;

        /**
         * Endpoint to retrieve all authors with pagination.
         *
         * @param page The page number for pagination (default is 0).
         * @param size The size of each page (default is 10).
         * @return ResponseEntity with a page of AuthorResponse objects.
         */
        @GetMapping
        @ApiOperation(value = "Get all authors with pagination")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Successfully retrieved authors"),
                        @ApiResponse(code = 500, message = "Internal server error")
        })
        public ResponseEntity<Page<AuthorResponse>> getAllAuthors(
                        @RequestParam(defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                        @RequestParam(defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
                Pageable pageable = PageRequest.of(page, size);
                return ResponseEntity.ok(authorService.findAll(pageable));
        }

        /**
         * Endpoint to retrieve an author by ID.
         *
         * @param id The ID of the author to retrieve.
         * @return ResponseEntity with the AuthorResponse object corresponding to the
         *         ID.
         */
        @GetMapping("/{id}")
        @ApiOperation(value = "Get author by ID")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Successfully retrieved author"),
                        @ApiResponse(code = 404, message = "Author not found"),
                        @ApiResponse(code = 500, message = "Internal server error")
        })
        public ResponseEntity<AuthorResponse> getAuthorById(
                        @ApiParam(value = "ID of the author to retrieve", required = true) @PathVariable final Long id) {
                return ResponseEntity.ok(authorService.get(id));

        }

        /**
         * Endpoint to create a new author.
         *
         * @param authorRequest The request body containing author data to be created.
         *                      Required fields include author's name, birth date,
         *                      nationality, and description.
         * @return ResponseEntity with the HTTP status code 201 (Created) and the ID of
         *         the created author.
         */
        @PostMapping
        @ApiOperation(value = "Create a new author")
        @ApiResponses(value = {
                        @ApiResponse(code = 201, message = "Author successfully created"),
                        @ApiResponse(code = 400, message = "Invalid input data"),
                        @ApiResponse(code = 500, message = "Internal server error")
        })
        public ResponseEntity<Long> createAuthor(
                        @ApiParam(value = "Author data to create", required = true) @Valid @RequestBody final AuthorRequest authorRequest) {
                return new ResponseEntity<>(authorService.create(authorRequest), HttpStatus.CREATED);
        }

        /**
         * Endpoint to update an existing author.
         *
         * @param id            The ID of the author to update.
         * @param authorRequest The request body containing updated author data.
         *                      Required fields include author's name, birth date,
         *                      nationality, and description.
         * @return ResponseEntity with a success message indicating the author was
         *         updated successfully.
         */
        @PutMapping("/{id}")
        @ApiOperation(value = "Update an existing author")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Author successfully updated"),
                        @ApiResponse(code = 400, message = "Invalid input data"),
                        @ApiResponse(code = 404, message = "Author not found"),
                        @ApiResponse(code = 500, message = "Internal server error")
        })
        public ResponseEntity<String> updateAuthor(
                        @ApiParam(value = "ID of the author to update", required = true) @PathVariable final Long id,
                        @ApiParam(value = "Author data to update", required = true) @Valid @RequestBody final AuthorRequest authorRequest) {
                authorService.update(id, authorRequest);
                String message = String.format(MessagesConstants.AUTHOR_UPDATE_SUCCESS, id);
                return ResponseEntity.ok(message);
        }

        /**
         * Endpoint to delete an author by ID.
         *
         * @param id The ID of the author to delete.
         * @return ResponseEntity with a success message indicating the author was
         *         deleted successfully.
         */
        @DeleteMapping("/{id}")
        @ApiOperation(value = "Delete an author by ID")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Author successfully deleted"),
                        @ApiResponse(code = 404, message = "Author not found"),
                        @ApiResponse(code = 500, message = "Internal server error")
        })
        public ResponseEntity<String> deleteAuthor(
                        @ApiParam(value = "ID of the author to delete", required = true) @PathVariable final Long id) {
                authorService.delete(id);
                String message = String.format(MessagesConstants.AUTHOR_DELETE_SUCCESS, id);
                return ResponseEntity.ok(message);
        }

        /**
         * Endpoint to search authors by keyword in their names.
         *
         * @param keyword The keyword to search for in author names.
         * @return ResponseEntity with a list of AuthorResponse objects matching the
         *         search criteria.
         */
        @GetMapping("/search")
        @ApiOperation(value = "Search authors by keyword")
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Successfully retrieved authors"),
                        @ApiResponse(code = 500, message = "Internal server error")
        })
        public ResponseEntity<List<AuthorResponse>> searchAuthorsByKeyword(
                        @ApiParam(value = "Keyword to search in author names", required = true) @RequestParam("q") final String keyword) {
                List<AuthorResponse> authors = authorService.search(keyword);
                return ResponseEntity.ok(authors);
        }
}