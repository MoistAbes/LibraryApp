package com.example.libraryproject.repository;

import com.example.libraryproject.enitity.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReaderRepositoryTestSuite {

    @Autowired
    private ReaderRepository repository;

    @Test
    void findAllTest(){

        //Given
        Reader reader1 = new Reader("test name", "test surname");
        Reader reader2 = new Reader("test name2", "test surname2");
        Reader reader3 = new Reader("test name3", "test surname3");

        //When
        Long reader1Id = repository.save(reader1).getId();
        Long reader2Id = repository.save(reader2).getId();
        Long reader3Id = repository.save(reader3).getId();
        List<Reader> savedReaders = repository.findAll();

        //Then
        assertEquals(3, savedReaders.size());

        //Clean up
        repository.deleteById(reader1Id);
        repository.deleteById(reader2Id);
        repository.deleteById(reader3Id);
    }

    @Test
    void findByIdTest(){

        //Given
        Reader reader = new Reader("test name", "test surname");

        //When
        Long readerId = repository.save(reader).getId();

        Optional<Reader> resultReader = repository.findById(readerId);

        //Then
        assertEquals("test name", resultReader.get().getFirstname());
        assertEquals("test surname", resultReader.get().getLastname());

        //Clean up
        repository.deleteById(readerId);
    }

    @Test
    void deleteByIdTest(){

        //Given
        Reader reader = new Reader("test name", "test surname");
        Long readerId = repository.save(reader).getId();

        //When
        repository.deleteById(readerId);
        List<Reader> readers = repository.findAll();

        //Then
        assertEquals(0, readers.size());

        //Clean up
    }
}
