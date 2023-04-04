package sk.umb.prg3.library.library.book.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sk.umb.prg3.library.library.book.persistence.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}