package com.example.libraryproject.mapper;

import com.example.libraryproject.dto.BookDto;
import com.example.libraryproject.enitity.Book;
import com.example.libraryproject.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookMapper {

    private final TitleRepository titleRepository;

    public BookDto mapToBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getBookStatus(),
                book.getTitle().getId()
        );
    }

    public Book mapToBook(BookDto bookDto){
        return new Book(
                bookDto.getId(),
                titleRepository.findById(bookDto.getTitleId()).get(),
                bookDto.getBookStatus()
        );
    }

    public List<BookDto> mapToBookDtoList(List<Book> books){
        return books.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }

}
