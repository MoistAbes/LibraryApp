package com.example.libraryproject.controller;

import com.example.libraryproject.dto.BookDto;
import com.example.libraryproject.enitity.Book;
import com.example.libraryproject.exception.BookNotFoundException;
import com.example.libraryproject.mapper.BookMapper;
import com.example.libraryproject.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookDbService service;
    private final BookMapper mapper;

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks(){
        List<Book> books = service.getAllBooks();
        return ResponseEntity.ok(mapper.mapToBookDtoList(books));
    }

    @GetMapping(value = "{bookId}")
    public ResponseEntity<BookDto> getReader(@PathVariable Long bookId) throws BookNotFoundException {
        Book book = service.getBook(bookId);
        return ResponseEntity.ok(mapper.mapToBookDto(book));
    }

    @GetMapping("/bookAmount/{title}")
    public ResponseEntity<Long> getBookAmount(@PathVariable String title){
        return ResponseEntity.ok(service.getBooksAmountByTitle(title));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto){
        Book book = mapper.mapToBook(bookDto);
        service.saveBook(book);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto){
        Book book = mapper.mapToBook(bookDto);
        Book savedBook = service.saveBook(book);
        return ResponseEntity.ok(mapper.mapToBookDto(savedBook));
    }


}
