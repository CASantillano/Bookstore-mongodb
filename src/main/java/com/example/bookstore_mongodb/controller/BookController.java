package com.example.bookstore_mongodb.controller;

import com.example.bookstore_mongodb.entity.Book;
import com.example.bookstore_mongodb.repository.BookRepository;
import com.example.bookstore_mongodb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @DeleteMapping("api/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id){
        boolean isDeleted = bookService.deleteBook(id);
        if (isDeleted){
        //  return 200 ok with a success message
        return ResponseEntity.ok(Map.of("message", "Book deleted successfully"));
    } else {
        //  return 404 if the id is missing
        return ResponseEntity.status(404).body(Map.of("error", "Book ID not found"));
    }
    }
}