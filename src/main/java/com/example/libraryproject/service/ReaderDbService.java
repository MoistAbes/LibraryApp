package com.example.libraryproject.service;

import com.example.libraryproject.enitity.Reader;
import com.example.libraryproject.exception.ReaderNotFoundException;
import com.example.libraryproject.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderDbService {

    private final ReaderRepository repository;

    public List<Reader> getAllReaders(){
        return repository.findAll();
    }


    public Reader saveReader(final Reader reader){
        return repository.save(reader);
    }

    public void deleteReader(final Long readerId){
        repository.deleteById(readerId);
    }

    public Reader getReader(final Long readerId) throws ReaderNotFoundException {
        return repository.findById(readerId).orElseThrow(ReaderNotFoundException::new);
    }


}
