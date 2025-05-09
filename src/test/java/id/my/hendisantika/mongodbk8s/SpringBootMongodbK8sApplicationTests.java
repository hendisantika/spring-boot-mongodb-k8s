package id.my.hendisantika.mongodbk8s;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(MongoTestConfiguration.class)
class SpringBootMongodbK8sApplicationTests {

    @Test
    void contextLoads() {
    }

}
