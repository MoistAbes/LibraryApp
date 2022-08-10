package com.example.libraryproject.repository;

import com.example.libraryproject.domain.BookStatus;
import com.example.libraryproject.enitity.Book;
import com.example.libraryproject.enitity.Reader;
import com.example.libraryproject.enitity.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TitleRepositoryTestSuite {

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAllTest(){

        //Given
        List<Book> books1 = Arrays.asList(
                new Book(BookStatus.FREE),
                new Book(BookStatus.DESTROYED),
                new Book(BookStatus.FREE)
        );

        List<Book> books2 = Arrays.asList(
                new Book(BookStatus.RENTED),
                new Book(BookStatus.LOST),
                new Book(BookStatus.FREE)
        );

        List<Book> books3 = Arrays.asList(
                new Book(BookStatus.FREE),
                new Book(BookStatus.FREE),
                new Book(BookStatus.FREE)
        );

        Title title1 = new Title("test title", "test author", LocalDate.of(1234, 11, 5), books1);
        books1.forEach(book -> book.setTitle(title1));

        Title title2 = new Title("test title2", "test author2", LocalDate.of(6171, 5, 6), books2);
        books2.forEach(book -> book.setTitle(title2));

        Title title3 = new Title("test title3", "test author3", LocalDate.of(1996, 2, 1), books3);
        books3.forEach(book -> book.setTitle(title3));

        //When
        Long titleId1 = titleRepository.save(title1).getId();
        Long titleId2 = titleRepository.save(title2).getId();
        Long titleId3 = titleRepository.save(title3).getId();

        bookRepository.saveAll(books1);
        bookRepository.saveAll(books2);
        bookRepository.saveAll(books3);

        List<Title> savedTitles = titleRepository.findAll();

        //Then
        assertEquals(3, savedTitles.size());

        //Clean up
        bookRepository.deleteAll();

        titleRepository.deleteById(titleId1);
        titleRepository.deleteById(titleId2);
        titleRepository.deleteById(titleId3);
    }

    @Test
    void findByIdTest(){

        //Given
        List<Book> books1 = Arrays.asList(
                new Book(BookStatus.FREE),
                new Book(BookStatus.DESTROYED),
                new Book(BookStatus.FREE)
        );

        Title title1 = new Title("test title", "test author", LocalDate.of(1234, 11, 5), books1);
        books1.forEach(book -> book.setTitle(title1));

        //When
        Title savedTitle = titleRepository.save(title1);
        bookRepository.saveAll(books1);

        //Then
        assertEquals("test title", savedTitle.getTitle());
        assertEquals("test author", savedTitle.getAuthor());
        assertEquals(3, savedTitle.getBookList().size());

        //Clean up
        bookRepository.deleteAll();
        titleRepository.deleteById(savedTitle.getId());

    }

    @Test
    void deleteByIdTest(){

        //Given
        List<Book> books1 = Arrays.asList(
                new Book(BookStatus.FREE),
                new Book(BookStatus.DESTROYED),
                new Book(BookStatus.FREE)
        );

        Title title1 = new Title("test title", "test author", LocalDate.of(1234, 11, 5), books1);
        books1.forEach(book -> book.setTitle(title1));

        Title savedTitle = titleRepository.save(title1);
        bookRepository.saveAll(books1);

        //When
        titleRepository.deleteById(savedTitle.getId());

        //Then
        assertEquals(0, titleRepository.findAll().size());
        assertEquals(0, bookRepository.findAll().size());

        //Clean up

    }

}
