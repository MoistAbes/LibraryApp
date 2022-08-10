package com.example.libraryproject.dto;

import com.example.libraryproject.domain.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {

    private Long id;
    private BookStatus bookStatus;
    private Long titleId;

}
