package com.example.libraryproject.controller;

import com.example.libraryproject.dto.RentDto;
import com.example.libraryproject.enitity.Rent;
import com.example.libraryproject.exception.RentNotFoundException;
import com.example.libraryproject.mapper.RentMapper;
import com.example.libraryproject.service.BookDbService;
import com.example.libraryproject.service.RentDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/rents")
public class RentController {

    private final RentDbService rentDbService;
    private final BookDbService bookDbService;
    private final RentMapper mapper;

    @GetMapping
    public ResponseEntity<List<RentDto>> getRents(){
        List<Rent> rents = rentDbService.getAllRents();
        return ResponseEntity.ok(mapper.mapToRentDtoList(rents));
    }

    @GetMapping(value = "{rentId}")
    public ResponseEntity<RentDto> getRent(@PathVariable Long rentId) throws RentNotFoundException {
        Rent rent = rentDbService.getRent(rentId);
        return ResponseEntity.ok(mapper.mapToRentDto(rent));
    }

    @DeleteMapping(value = "{rentId}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long rentId) throws RentNotFoundException {
        Rent rent = rentDbService.getRent(rentId);
        bookDbService.returnBookById(rent.getBook().getId());
        rentDbService.deleteRent(rentId);
        return ResponseEntity.ok().build();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRent(@RequestBody RentDto rentDto){
        Rent rent = mapper.mapToRent(rentDto);
        bookDbService.rentBookById(rent.getBook().getId());
        rentDbService.saveRent(rent);
        return ResponseEntity.ok().build();
    }
}
