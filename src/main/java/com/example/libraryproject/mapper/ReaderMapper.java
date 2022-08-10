package com.example.libraryproject.mapper;

import com.example.libraryproject.dto.ReaderDto;
import com.example.libraryproject.enitity.Reader;
import com.example.libraryproject.enitity.Rent;
import com.example.libraryproject.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderMapper {

    private final RentRepository rentRepository;

    public ReaderDto mapToReaderDto(Reader reader){
        return new ReaderDto(
                reader.getId(),
                reader.getFirstname(),
                reader.getLastname(),
                reader.getAccountCreated(),
                reader.getRents().stream()
                        .map(Rent::getId)
                        .collect(Collectors.toList())
        );
    }

    public Reader mapToReader(ReaderDto readerDto){
        return new Reader(
                readerDto.getId(),
                readerDto.getFirstname(),
                readerDto.getLastname(),
                readerDto.getAccountCreated(),
                rentRepository.findAll().stream()
                        .filter(rent -> rent.getReader().getId().equals(readerDto.getId()))
                        .collect(Collectors.toList())
        );
    }

    public List<ReaderDto> mapToReaderDtoList(List<Reader> readers){
        return readers.stream()
                .map(this::mapToReaderDto)
                .collect(Collectors.toList());
    }

}
