package com.example.libraryproject.controller;

import com.example.libraryproject.dto.ReaderDto;
import com.example.libraryproject.enitity.Reader;
import com.example.libraryproject.exception.ReaderNotFoundException;
import com.example.libraryproject.mapper.ReaderMapper;
import com.example.libraryproject.service.ReaderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/readers")
public class ReaderController {

    private final ReaderDbService service;
    private final ReaderMapper mapper;

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getReaders(){
        List<Reader> readers = service.getAllReaders();
        return ResponseEntity.ok(mapper.mapToReaderDtoList(readers));
    }

    @GetMapping(value = "{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable Long readerId) throws ReaderNotFoundException {
        Reader reader = service.getReader(readerId);
        return ResponseEntity.ok(mapper.mapToReaderDto(reader));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto){
        Reader reader = mapper.mapToReader(readerDto);
        service.saveReader(reader);
        return ResponseEntity.ok().build();
    }

}
