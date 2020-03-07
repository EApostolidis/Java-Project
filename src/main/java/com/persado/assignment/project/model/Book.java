package com.persado.assignment.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BOOKS")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String summary;

    private long isbn;

    private int copiesPurchased;

    private int available;

    @ManyToMany
    @JoinTable(name = "USER_BOOK",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
    private Set<User> users;
}
