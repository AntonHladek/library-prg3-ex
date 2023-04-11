package sk.umb.prg3.library.library.author.service;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import sk.umb.prg3.library.library.author.persistence.entity.AuthorEntity;
import sk.umb.prg3.library.library.author.persistence.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDetailDto> getAllAuthors() {
        return mapToDtoList(authorRepository.findAll());
    }

    public AuthorDetailDto getAuthorById(Long authorId) {
        return mapToDto(getAuthorEntityById(authorId));
    }

    public void createAuthor(AuthorDetailDto author) {
        AuthorEntity ce = new AuthorEntity();
        ce.setFirstName(author.getFirstName());
        ce.setLastName(author.getLastName());
        authorRepository.save(ce);
    }

    public void updateAuthor(Long authorId, AuthorDetailDto authorDetailDto) {
        AuthorEntity author = getAuthorEntityById(authorId);

        if (! Strings.isEmpty(authorDetailDto.getFirstName())) {
            author.setFirstName(authorDetailDto.getFirstName());
        }

        if (! Strings.isEmpty(authorDetailDto.getLastName())) {
            author.setLastName(authorDetailDto.getLastName());
        }

        authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    private AuthorEntity getAuthorEntityById(Long authorId) {
        Optional<AuthorEntity> author = authorRepository.findById(authorId);

        if (author.isEmpty()) {
            throw new IllegalArgumentException("Customer not found. ID: " + authorId);
        }

        return author.get();
    }

    private List<AuthorDetailDto> mapToDtoList(Iterable<AuthorEntity> authorEntities) {
        List<AuthorDetailDto> author = new ArrayList<>();

        authorEntities.forEach(authorEntity -> {
            AuthorDetailDto dto = mapToDto(authorEntity);
            author.add(dto);
        });

        return author;
    }

    private AuthorDetailDto mapToDto(AuthorEntity customerEntity) {
        AuthorDetailDto dto = new AuthorDetailDto();
        dto.setFirstName(customerEntity.getFirstName());
        dto.setLastName(customerEntity.getLastName());
        dto.setId(customerEntity.getId());
        return dto;
    }


}
