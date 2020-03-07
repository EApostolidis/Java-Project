package com.persado.assignment.project.service;

import com.persado.assignment.project.model.Book;
import com.persado.assignment.project.model.User;
import com.persado.assignment.project.repository.BookRepository;
import com.persado.assignment.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ManagerService {

    private final UserRepository userRepository;

    private final BookRepository bookRepository;

    @Transactional
    public Book loanBook(User user, Book book) throws Exception {
        if (book.getAvailable() > 0) {
            book.setAvailable(book.getAvailable() - 1);
            book.getUsers().add(user);
            bookRepository.save(book);
            user.getBooks().add(book);
            userRepository.save(user);
            return book;
        } else {
            throw new Exception("There are no available books");
        }
    }

    public Book returnBook(User user, Book book) {
        user.getBooks().remove(book);
        book.setAvailable(book.getAvailable() + 1);
        bookRepository.save(book);
        userRepository.save(user);
        return book;
    }
}
