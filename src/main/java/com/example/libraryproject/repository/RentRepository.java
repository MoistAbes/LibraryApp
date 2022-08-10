package com.example.libraryproject.repository;

import com.example.libraryproject.enitity.Reader;
import com.example.libraryproject.enitity.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {

    @Override
    List<Rent> findAll();

    @Override
    Optional<Rent> findById(Long id);

    @Override
    Rent save(Rent rent);
}
