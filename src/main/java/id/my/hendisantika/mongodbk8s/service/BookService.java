package id.my.hendisantika.mongodbk8s.service;

import id.my.hendisantika.mongodbk8s.model.Book;
import id.my.hendisantika.mongodbk8s.repository.BookRepository;
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
 * Time: 05.56
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book create(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        Book existingBook = bookRepository.findById(book.getId()).orElse(null);
        if (Objects.isNull(existingBook)) {
            throw new RuntimeException("Book Id is not found");
        }
        existingBook.setDescription(book.getDescription());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPage(book.getPage());
        existingBook.setTitle(book.getTitle());
        existingBook.setPrice(book.getPrice());

        return bookRepository.save(existingBook);
    }

    public Book getOne(String id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book Id is not found"));
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void delete(String id) {
        bookRepository.deleteById(id);
    }
}
