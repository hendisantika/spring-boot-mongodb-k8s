package id.my.hendisantika.mongodbk8s.controller;

import id.my.hendisantika.mongodbk8s.model.Author;
import id.my.hendisantika.mongodbk8s.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    /**
     * {@code PUT  /author} : Updates an existing author.
     *
     * @param author the author to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated author,
     * or with status {@code 400 (Bad Request)} if the author is not valid,
     * or with status {@code 500 (Internal Server Error)} if the author couldn't be updated.
     */
    @PutMapping()
    public ResponseEntity<Author> updateAuthor(@Valid @RequestBody Author author) {
        log.debug("REST request to update Author : {}", author);
        Author result = authorService.update(author);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /author} : get all the authors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of author in body.
     */

    @GetMapping()
    public ResponseEntity<List<Author>> getAllAuthor() {
        log.debug("REST request to get all authors");
        List<Author> lst = authorService.getAll();

        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    /**
     * {@code GET  /author/:id} : get the "id" author.
     *
     * @param id the id of the author to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the author, or with status {@code 404 (Not Found)}.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Author> getOneAuthor(@PathVariable("id") String id) {
        log.debug("REST request to get Author : {}", id);
        Author e = authorService.getOne(id);

        return new ResponseEntity<>(e, HttpStatus.OK);
    }
}
