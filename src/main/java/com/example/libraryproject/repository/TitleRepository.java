package com.example.libraryproject.repository;

import com.example.libraryproject.enitity.Reader;
import com.example.libraryproject.enitity.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository

public interface TitleRepository extends CrudRepository<Title, Long> {

    @Override
    List<Title> findAll();

    @Override
    Optional<Title> findById(Long id);

    @Override
    Title save(Title title);

}
