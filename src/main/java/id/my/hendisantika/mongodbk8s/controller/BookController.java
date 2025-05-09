package id.my.hendisantika.mongodbk8s.controller;

import id.my.hendisantika.mongodbk8s.model.Book;
import id.my.hendisantika.mongodbk8s.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
 * Time: 06.07
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    /**
     * {@code POST  /book} : Create a new book.
     *
     * @param book the book to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new book.
     */
    @PostMapping()
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
        log.debug("REST request to save Book : {}", book);
        return new ResponseEntity<>(bookService.create(book), HttpStatus.CREATED);
    }

    /**
     * {@code PUT  /book} : Updates an existing book.
     *
     * @param book the book to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated book,
     * or with status {@code 400 (Bad Request)} if the book is not valid,
     * or with status {@code 500 (Internal Server Error)} if the book couldn't be updated.
     */
    @PutMapping()
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) {
        log.debug("REST request to update Book : {}", book);
        Book result = bookService.update(book);
        return ResponseEntity.ok().body(result);
    }
}
