package sk.umb.prg3.library.library.book.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sk.umb.prg3.library.library.book.persistence.entity.Book;
import sk.umb.prg3.library.library.book.persistence.repository.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable(value = "id") Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            return ResponseEntity.ok().body(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long bookId,
            @Valid @RequestBody Book bookDetails) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {

            Book updatedBook = book.get();
            updatedBook.setName(bookDetails.getName());
            updatedBook.setIsbn(bookDetails.getIsbn());
            updatedBook.setAuthorFirstName(bookDetails.getAuthorFirstName());
            updatedBook.setAuthorLastName(bookDetails.getAuthorLastName());
            updatedBook.setBookCount(bookDetails.getBookCount());

            Book savedBook = bookRepository.save(updatedBook);
            return ResponseEntity.ok().body(savedBook);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {

            bookRepository.delete(book.get());
            return ResponseEntity.ok().build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
