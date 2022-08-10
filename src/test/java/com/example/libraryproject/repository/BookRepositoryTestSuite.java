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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookRepositoryTestSuite {

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

        Title title1 = new Title("test title", "test author", LocalDate.of(1234, 11, 5), books1);
        books1.forEach(book -> book.setTitle(title1));

        //When
        Long titleId1 = titleRepository.save(title1).getId();
        bookRepository.saveAll(books1);
        List<Book> savedBooks = bookRepository.findAll();

        //Then
        assertEquals(3, savedBooks.size());

        //Clean up
        bookRepository.deleteAll();
        titleRepository.deleteById(titleId1);

    }

    @Test
    void findByIdTest(){

        //Given
        List<Book> books1 = List.of(
                new Book(BookStatus.FREE)
        );

        Title title1 = new Title("test title", "test author", LocalDate.of(1234, 11, 5), books1);
        books1.forEach(book -> book.setTitle(title1));

        //When
        Title savedTitle = titleRepository.save(title1);
        Book savedBook = bookRepository.save(books1.get(0));

        //Then
        assertEquals(BookStatus.FREE, savedBook.getBookStatus());

        //Clean up
        bookRepository.deleteById(savedBook.getId());
        titleRepository.deleteById(savedTitle.getId());

    }

    @Test
    void shouldDeleteBookNotTitle(){

       ///Given
        List<Book> books1 = List.of(
                new Book(BookStatus.FREE)
        );

        Title title1 = new Title("test title", "test author", LocalDate.of(1234, 11, 5), books1);
        books1.forEach(book -> book.setTitle(title1));

        Title savedTitle = titleRepository.save(title1);
        Book savedBook = bookRepository.save(books1.get(0));

        //When
        bookRepository.deleteById(savedBook.getId());

        //Then
        assertTrue(titleRepository.existsById(savedTitle.getId()));
        //Clean up
        titleRepository.deleteById(savedTitle.getId());

    }

    @Test
    void shouldDeleteTitleAndBook(){

        ///Given
        List<Book> books1 = List.of(
                new Book(BookStatus.FREE)
        );

        Title title1 = new Title("test title", "test author", LocalDate.of(1234, 11, 5), books1);
        books1.forEach(book -> book.setTitle(title1));

        Title savedTitle = titleRepository.save(title1);
        Book savedBook = bookRepository.save(books1.get(0));

        //When
        titleRepository.deleteById(savedTitle.getId());

        //Then
        assertFalse(bookRepository.existsById(savedBook.getId()));

        //Clean up

    }

    @Test
    void shouldUpdateBookStatus(){
        ///Given
        List<Book> books1 = List.of(
                new Book(BookStatus.FREE)
        );

        Title title1 = new Title("test title", "test author", LocalDate.of(1234, 11, 5), books1);
        books1.forEach(book -> book.setTitle(title1));

        Title savedTitle = titleRepository.save(title1);
        Book savedBook = bookRepository.save(books1.get(0));

        //When
        savedBook.setBookStatus(BookStatus.RENTED);

        Book updatedBook = bookRepository.save(savedBook);

        //Then
        assertEquals(BookStatus.RENTED, updatedBook.getBookStatus());

        //Clean up
        titleRepository.deleteById(savedTitle.getId());

    }

}
