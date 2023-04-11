package sk.umb.prg3.library.library.author.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import sk.umb.prg3.library.library.author.service.AuthorDetailDto;
import sk.umb.prg3.library.library.author.service.AuthorService;


import java.util.List;

@RestController

public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // list resources
    @GetMapping("/api/authors")
    public List<AuthorDetailDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    // retrieve detail
    @GetMapping("/api/authors/{authorId}")
    public AuthorDetailDto getAuthor(@PathVariable Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    // create resource
    @PostMapping("/api/authors")
    public void createAuthor(@RequestBody AuthorDetailDto author) {
        authorService.createAuthor(author);
    }

    // update resource
    @PutMapping("/api/authors/{authorId}")
    public void updateAuthor(@PathVariable Long authorId, @RequestBody AuthorDetailDto author) {
        System.out.println("Update customer called: ID = " + authorId);
        authorService.updateAuthor(authorId, author);
    }

    // delete resource
    @DeleteMapping("/api/authors/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}
