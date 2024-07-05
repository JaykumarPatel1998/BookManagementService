package com.jaykumar.borrowingservice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class BookController {

    private final IBookClient bookClient;

    @GetMapping("/books")
    public ResponseEntity<?> getBooks(){
            return bookClient.getBooks();
    }

    @GetMapping("{id}")
    public Book getBookById(@PathVariable Long id){
        ResponseEntity<?> response = bookClient.getBookById(id);
        if(response.getStatusCode() == HttpStatus.OK) {
            LinkedHashMap map = (LinkedHashMap) response.getBody();


            Book responseBook = new Book();

            if(map.get("status").equals(BookStatus.AVAILABLE.toString())) {
                responseBook.setStatus(BookStatus.AVAILABLE);
            } else {
                responseBook.setStatus(BookStatus.BORROWED);
            }

            if(map.get("id") instanceof Integer) {
                responseBook.setId((Integer) map.get("id"));
            }

            if(map.get("name") instanceof String) {
                responseBook.setName((String) map.get("name"));
            }



            return responseBook;
        } else {
            return null;
        }
    }

    @PostMapping("/borrow/{id}")
    public ResponseEntity<String> borrowBookById(@PathVariable Long id){
        Book book = getBookById(id);
        if(book != null && book.getStatus() == BookStatus.AVAILABLE) {
            book.setStatus(BookStatus.BORROWED);
            bookClient.updateBook(book);
            return new ResponseEntity<>("Book Borrowed Successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book cannot be borrowed!", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<String> returnBookById(@PathVariable Long id){
        Book book = getBookById(id);
        if(book.getStatus() == BookStatus.BORROWED) {
            book.setStatus(BookStatus.AVAILABLE);
            bookClient.updateBook(book);
            return new ResponseEntity<>("Book Returned Successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book cannot be returned!", HttpStatus.CONFLICT);
        }
    }



}
