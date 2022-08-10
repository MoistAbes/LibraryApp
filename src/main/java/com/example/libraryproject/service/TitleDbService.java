package com.example.libraryproject.service;

import com.example.libraryproject.enitity.Book;
import com.example.libraryproject.enitity.Title;
import com.example.libraryproject.exception.BookNotFoundException;
import com.example.libraryproject.exception.TitleNotFoundException;
import com.example.libraryproject.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleDbService {

    private final TitleRepository repository;

    public List<Title> getAllTitles(){
        return repository.findAll();
    }

    public Title saveTitle(final Title title){
        return repository.save(title);
    }

    public void deleteTitle(final Long titleId){
        repository.deleteById(titleId);
    }

    public Title getTitle(final Long titleId) throws TitleNotFoundException {
        return repository.findById(titleId).orElseThrow(TitleNotFoundException::new);
    }

}
