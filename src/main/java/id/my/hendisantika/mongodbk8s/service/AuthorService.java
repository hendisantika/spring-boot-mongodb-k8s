package id.my.hendisantika.mongodbk8s.service;

import id.my.hendisantika.mongodbk8s.model.Author;
import id.my.hendisantika.mongodbk8s.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongodb-k8s
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 10/05/25
 * Time: 05.54
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author create(Author author) {
        return authorRepository.save(author);
    }

    public Author update(Author author) {
        Author existingAuthor = authorRepository.findById(author.getId()).orElse(null);
        if (Objects.isNull(existingAuthor)) {
            throw new RuntimeException("Author Id is not found");
        }
        existingAuthor.setFirstname(author.getFirstname());
        existingAuthor.setLastname(author.getLastname());

        return authorRepository.save(existingAuthor);
    }

    public Author getOne(String id) {
        return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author Id is not found"));
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public void delete(String id) {
        authorRepository.deleteById(id);
    }
}
