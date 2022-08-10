package com.example.libraryproject.enitity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "titles")
public class Title {

    @Id
    @GeneratedValue
    @Column(name = "titleId", unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "releaseDate")
    private LocalDate releaseDate;

    @OneToMany(
            cascade = CascadeType.ALL,
            targetEntity = Book.class,
            mappedBy = "title",
            fetch = FetchType.LAZY
    )
    private List<Book> bookList = new ArrayList<>();

    public Title(Long id, String title, String author, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
    }

    public Title(String title, String author, LocalDate releaseDate, List<Book> bookList) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.bookList = bookList;
    }


    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
