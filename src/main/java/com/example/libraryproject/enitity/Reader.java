package com.example.libraryproject.enitity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "readers")
public class Reader {

    @Id
    @GeneratedValue
    @Column(name = "readerId", unique = true)
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    private LocalDateTime accountCreated;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "reader",
            fetch = FetchType.EAGER
    )
    private List<Rent> rents = new ArrayList<>();

    public Reader(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
