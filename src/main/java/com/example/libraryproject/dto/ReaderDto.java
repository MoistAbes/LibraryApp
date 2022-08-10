package com.example.libraryproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ReaderDto {

    private Long id;
    private String firstname;
    private String lastname;
    private LocalDateTime accountCreated;
    private List<Long> rents;

}
