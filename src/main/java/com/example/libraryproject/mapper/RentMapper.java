package com.example.libraryproject.mapper;

import com.example.libraryproject.dto.RentDto;
import com.example.libraryproject.enitity.Rent;
import com.example.libraryproject.repository.BookRepository;
import com.example.libraryproject.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentMapper {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public RentDto mapToRentDto(Rent rent){
        return new RentDto(
                rent.getId(),
                rent.getBook().getId(),
                rent.getReader().getId(),
                rent.getRentFrom(),
                rent.getRentTo()
        );
    }

    public Rent mapToRent(RentDto rentDto){
        return new Rent(
                rentDto.getId(),
                bookRepository.findById(rentDto.getBookId()).get(),
                readerRepository.findById(rentDto.getReaderId()).get(),
                rentDto.getRentFrom(),
                rentDto.getRentTo()
        );
    }

    public List<RentDto> mapToRentDtoList(List<Rent> rents){
        return rents.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }

}
