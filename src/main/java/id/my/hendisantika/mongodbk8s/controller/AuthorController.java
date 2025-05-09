package id.my.hendisantika.mongodbk8s.controller;

import id.my.hendisantika.mongodbk8s.model.Author;
import id.my.hendisantika.mongodbk8s.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongodb-k8s
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 10/05/25
 * Time: 06.00
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    /**
     * {@code POST  /author} : Create a new author.
     *
     * @param author the author to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new author.
     */
    @PostMapping()
    public ResponseEntity<Author> createAuthor(@RequestBody @Valid Author author) {
        log.debug("REST request to save Author : {}", author);
        return new ResponseEntity<>(authorService.create(author), HttpStatus.CREATED);
    }

}
