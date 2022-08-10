package com.example.libraryproject.enitity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rents")
public class Rent {

    @Id
    @GeneratedValue
    @Column(name = "rentId", unique = true)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookId")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Reader reader;

    @Column(name = "rentFrom")
    private LocalDate rentFrom;

    @Column(name = "rentTo")
    private LocalDate rentTo;

    public Rent(Book book, Reader reader, LocalDate rentFrom, LocalDate rentTo) {
        this.book = book;
        this.reader = reader;
        this.rentFrom = rentFrom;
        this.rentTo = rentTo;
    }
}
