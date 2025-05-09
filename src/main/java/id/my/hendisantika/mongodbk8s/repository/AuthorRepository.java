package id.my.hendisantika.mongodbk8s.repository;

import id.my.hendisantika.mongodbk8s.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongodb-k8s
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 10/05/25
 * Time: 05.53
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {
}
