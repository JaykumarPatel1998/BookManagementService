package com.jaykumar.bookservice;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DBSeeder implements CommandLineRunner {

    private final IBookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Book> movies = List.of(
                Book.builder().name("Game Of Thrones").status(BookStatus.AVAILABLE).build(),
                Book.builder().name("Rich dad poor dad").status(BookStatus.BORROWED).build(),
                Book.builder().name("Don Quixote").status(BookStatus.BORROWED).build()
        );
        bookRepository.saveAll(movies);

        bookRepository.findAll().forEach((movie ->
                System.out.println(movie.getId() + " " + movie.getName())
        ));
    }
}
