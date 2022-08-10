package com.example.libraryproject.repository;

import com.example.libraryproject.domain.BookStatus;
import com.example.libraryproject.enitity.Book;
import com.example.libraryproject.enitity.Reader;
import com.example.libraryproject.enitity.Rent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentRepositoryTestSuite {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Test
    void findAllTest(){
        //Given
        Book book = new Book(BookStatus.FREE);
        Book book2 = new Book(BookStatus.FREE);
        Reader reader = new Reader("firstname test", "surname test");
        Rent rent = new Rent(
                book,
                reader,
                LocalDate.of(1997, 11, 23),
                LocalDate.of(1997, 12, 12)
        );

        Rent rent2 = new Rent(
                book2,
                reader,
                LocalDate.of(1997, 11, 23),
                LocalDate.of(1997, 12, 14)
        );

        //When
        Long bookId = bookRepository.save(book).getId();
        Long bookId2 = bookRepository.save(book2).getId();
        Long readerId = readerRepository.save(reader).getId();
        Long rentId = rentRepository.save(rent).getId();
        Long rentId2 = rentRepository.save(rent2).getId();

        List<Rent> rents = rentRepository.findAll();

        //Then
        assertEquals(2, rents.size());
        assertEquals("firstname test", rents.get(0).getReader().getFirstname());
        assertEquals("surname test", rents.get(0).getReader().getLastname());


        //Clean up
        rentRepository.deleteById(rentId);
        rentRepository.deleteById(rentId2);
        bookRepository.deleteById(bookId);
        bookRepository.deleteById(bookId2);
        readerRepository.deleteById(readerId);
    }

    @Test
    void findByIdTest(){
        //Given
        Book book = new Book(BookStatus.FREE);
        Book book2 = new Book(BookStatus.FREE);
        Reader reader = new Reader("firstname test", "surname test");
        Rent rent = new Rent(
                book,
                reader,
                LocalDate.of(1997, 11, 23),
                LocalDate.of(1997, 12, 12)
        );

        //When
        Long bookId = bookRepository.save(book).getId();
        Long bookId2 = bookRepository.save(book2).getId();
        Long readerId = readerRepository.save(reader).getId();
        Long rentId = rentRepository.save(rent).getId();

        Optional<Rent> savedRent = rentRepository.findById(rentId);

        //Then
        assertTrue(savedRent.isPresent());
        assertEquals("firstname test", savedRent.get().getReader().getFirstname());
        assertEquals("surname test", savedRent.get().getReader().getLastname());


        //Clean up
        rentRepository.deleteById(rentId);
        bookRepository.deleteById(bookId);
        bookRepository.deleteById(bookId2);
        readerRepository.deleteById(readerId);
    }

    @Test
    void shouldDeleteRentNotReader(){
        //Given
        Book book = new Book(BookStatus.RENTED);
        Reader reader = new Reader("firstname test", "surname test");
        Rent rent = new Rent(
                book,
                reader,
                LocalDate.of(1997, 11, 23),
                LocalDate.of(1997, 12, 12)
        );

        Long bookId = bookRepository.save(book).getId();
        Long readerId = readerRepository.save(reader).getId();
        Long rentId = rentRepository.save(rent).getId();

        //When
        rentRepository.deleteById(rentId);

        //Then
        assertFalse(rentRepository.existsById(rent.getId()));
        assertTrue(readerRepository.existsById(readerId));

        //Clean up
        readerRepository.deleteById(readerId);
        bookRepository.deleteById(bookId);

    }

    @Test
    void shouldDeleteRentNotBook(){
        //Given
        Book book = new Book(BookStatus.RENTED);
        Reader reader = new Reader("firstname test", "surname test");
        Rent rent = new Rent(
                book,
                reader,
                LocalDate.of(1997, 11, 23),
                LocalDate.of(1997, 12, 12)
        );

        Long bookId = bookRepository.save(book).getId();
        Long readerId = readerRepository.save(reader).getId();
        Long rentId = rentRepository.save(rent).getId();

        //When
        rentRepository.deleteById(rentId);

        //Then
        assertFalse(rentRepository.existsById(rent.getId()));
        assertTrue(bookRepository.existsById(bookId));

        //Clean up
        readerRepository.deleteById(readerId);
        bookRepository.deleteById(bookId);

    }



}
