package com.example.libraryproject.service;

import com.example.libraryproject.domain.BookStatus;
import com.example.libraryproject.enitity.Book;
import com.example.libraryproject.exception.BookNotFoundException;
import com.example.libraryproject.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDbService {
    private final BookRepository repository;

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public Book saveBook(final Book book){
        return repository.save(book);
    }

    public void deleteBook(final Long bookId){
        repository.deleteById(bookId);
    }

    public Book getBook(final Long bookId) throws BookNotFoundException {
        return repository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

    public Long getBooksAmountByTitle(final String title){
        return repository.findAll().stream()
                .filter(book -> book.getBookStatus().equals(BookStatus.FREE))
                .filter(book -> book.getTitle().getTitle().equals(title))
                .count();
    }

    public void returnBookById(final Long bookId){
        Optional<Book> book = repository.findById(bookId);
        book.ifPresent(b -> b.setBookStatus(BookStatus.FREE));
        repository.save(book.get());
    }

    public void rentBookById(final Long bookId){
        Optional<Book> book = repository.findById(bookId);
        book.ifPresent(b -> b.setBookStatus(BookStatus.RENTED));
        repository.save(book.get());
    }


}
