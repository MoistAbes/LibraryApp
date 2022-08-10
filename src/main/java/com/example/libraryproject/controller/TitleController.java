package com.example.libraryproject.controller;

import com.example.libraryproject.dto.BookDto;
import com.example.libraryproject.dto.TitleDto;
import com.example.libraryproject.enitity.Book;
import com.example.libraryproject.enitity.Title;
import com.example.libraryproject.exception.BookNotFoundException;
import com.example.libraryproject.exception.TitleNotFoundException;
import com.example.libraryproject.mapper.BookMapper;
import com.example.libraryproject.mapper.TitleMapper;
import com.example.libraryproject.service.BookDbService;
import com.example.libraryproject.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/titles")
public class TitleController {

    private final TitleDbService service;
    private final TitleMapper mapper;

    @GetMapping
    public ResponseEntity<List<TitleDto>> getTitles(){
        List<Title> titles = service.getAllTitles();
        return ResponseEntity.ok(mapper.mapToTitleDtoList(titles));
    }

    @GetMapping(value = "{titleId}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable Long titleId) throws TitleNotFoundException {
        Title title = service.getTitle(titleId);
        return ResponseEntity.ok(mapper.mapToTitleDto(title));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTitle(@RequestBody TitleDto titleDto){
        System.out.println("RELEASE DATE: " + titleDto.getReleaseDate());
        Title title = mapper.mapToTitle(titleDto);
        service.saveTitle(title);
        return ResponseEntity.ok().build();
    }

}
