package com.example.libraryproject.enitity;

import com.example.libraryproject.domain.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "books")
public class Book {


    @Id
    @GeneratedValue
    @Column(name = "bookId", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "titleId")
    private Title title;

    @Column(name = "status")
    private BookStatus bookStatus;

    public Book(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }


    public void setTitle(Title title) {
        this.title = title;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }
}
