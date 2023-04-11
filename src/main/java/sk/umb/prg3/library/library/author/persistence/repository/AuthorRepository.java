package sk.umb.prg3.library.library.author.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.prg3.library.library.author.persistence.entity.AuthorEntity;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {
    Iterable<AuthorEntity> findByLastName(String lastName);
}
