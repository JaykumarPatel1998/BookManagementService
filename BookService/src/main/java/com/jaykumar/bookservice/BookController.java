package com.jaykumar.bookservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("books")
public class BookController {

    private final IBookRepository bookRepository;

    @GetMapping("list")
    public ResponseEntity<List<Book>> getBooks(){
        //get the books from database and return them
        return new ResponseEntity<List<Book>>(
                bookRepository.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<?> getBookById(@RequestParam("id") Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()) {
            return new ResponseEntity<Book>(
                    book.get(),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    "Book not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            Book savedBook = bookRepository.save(book);
            return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while saving the book.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        if(existingBook.isPresent()) {
            try {
                Book updatedBook = bookRepository.save(book);
                return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("An error occurred while updating the book.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if(existingBook.isPresent()) {
            try {
                bookRepository.deleteById(id);
                return new ResponseEntity<>("Book deleted successfully.", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("An error occurred while deleting the book.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);
        }
    }
}
