package com.persado.assignment.project.controller;

import com.persado.assignment.project.model.Book;
import com.persado.assignment.project.service.BookService;
import com.persado.assignment.project.service.ManagerService;
import com.persado.assignment.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/bookshop")
public class BookController {

    private final BookService bookService;
    private final UserService userService;
    private final ManagerService managerService;

    @PostMapping(value = "books/")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.createBook(book));
    }


    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book Book = bookService.getBook(id);
        return ResponseEntity
                .ok()
                .body(Book);
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(null);
    }

    @GetMapping(value = "/books/")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity
                .ok()
                .body(bookService.getBooks());
    }

    @GetMapping(value = "/loan/")
    public ResponseEntity<Book> loanBook(
            @RequestParam(value = "bookId") long bookId,
            @RequestParam(value = "userId") long userId) throws Exception {
        Book Book = managerService.loanBook(userService.getUser(userId), bookService.getBook(bookId));
        return ResponseEntity
                .ok()
                .body(Book);
    }

    @GetMapping(value = "/return/")
    public ResponseEntity<Book> returnBook(
            @RequestParam(value = "bookId") long bookId,
            @RequestParam(value = "userId") long userId) {
        Book Book = managerService.returnBook(userService.getUser(userId), bookService.getBook(bookId));
        return ResponseEntity
                .ok()
                .body(Book);
    }
}
