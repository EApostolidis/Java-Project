package com.persado.assignment.project.dto;

import com.persado.assignment.project.model.Book;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDto {

    private String firstName;

    private String lastName;

    private String address;

    private Set<Book> books;
}
