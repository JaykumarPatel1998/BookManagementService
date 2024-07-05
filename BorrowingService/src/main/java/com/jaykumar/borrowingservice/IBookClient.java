package com.jaykumar.borrowingservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("BookService")
public interface IBookClient {

    @GetMapping("/books/list")
    public ResponseEntity<?> getBooks();

    @GetMapping("/books")
    public ResponseEntity<?> getBookById(@RequestParam("id") Long id);

    @PutMapping("/books")
    public ResponseEntity<?> updateBook(@RequestBody Book book);
}
