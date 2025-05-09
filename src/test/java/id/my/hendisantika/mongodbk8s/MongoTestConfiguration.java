package id.my.hendisantika.mongodbk8s;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongodb-k8s
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 10/05/25
 * Time: 06.23
 * To change this template use File | Settings | File Templates.
 */
@TestConfiguration
public class MongoTestConfiguration {

    @Container
    public static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest")
            .withExposedPorts(27017);

    static {
        mongoDBContainer.start();
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoClient mongoClient = MongoClients.create(mongoDBContainer.getReplicaSetUrl());
        return new MongoTemplate(mongoClient, "test");
    }
}
