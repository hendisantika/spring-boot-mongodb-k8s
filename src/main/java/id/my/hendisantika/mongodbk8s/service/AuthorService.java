package id.my.hendisantika.mongodbk8s.service;

import id.my.hendisantika.mongodbk8s.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final AuthorRepository repository;
}
