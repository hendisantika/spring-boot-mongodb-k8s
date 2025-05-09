package id.my.hendisantika.mongodbk8s.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongodb-k8s
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 10/05/25
 * Time: 05.51
 * To change this template use File | Settings | File Templates.
 */
@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    private String id;

    private String lastname;

    private String firstname;
}
