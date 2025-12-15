package com.spoorti.library_management.controller;

import com.spoorti.library_management.entity.Book;
import com.spoorti.library_management.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    // GET all books
    @GetMapping
    public List<Book> getAll() {
        return repo.findAll();
    }

    // GET one book by id
    @GetMapping("/{id}")
    public Book getOne(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    // CREATE new book
    @PostMapping
    public Book add(@RequestBody Book book) {
        return repo.save(book);
    }

    // UPDATE existing book
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        Book existing = repo.findById(id).orElseThrow();
        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        return repo.save(existing);
    }

    // DELETE book
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
