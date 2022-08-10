package com.example.libraryproject.service;

import com.example.libraryproject.enitity.Rent;
import com.example.libraryproject.enitity.Title;
import com.example.libraryproject.exception.RentNotFoundException;
import com.example.libraryproject.exception.TitleNotFoundException;
import com.example.libraryproject.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentDbService {
    private final RentRepository repository;

    public List<Rent> getAllRents(){
        return repository.findAll();
    }

    public Rent saveRent(final Rent rent){
        return repository.save(rent);
    }

    public void deleteRent(final Long rentId){
        repository.deleteById(rentId);
    }

    public Rent getRent(final Long rentId) throws RentNotFoundException {
        return repository.findById(rentId).orElseThrow(RentNotFoundException::new);
    }
}
