package id.my.hendisantika.mongodbk8s;

import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongodb-k8s
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 10/05/25
 * Time: 06.24
 * To change this template use File | Settings | File Templates.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@Import(MongoTestConfiguration.class)
public abstract class AbstractIntegrationTest {

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri",
                () -> MongoTestConfiguration.mongoDBContainer.getReplicaSetUrl());
    }
}