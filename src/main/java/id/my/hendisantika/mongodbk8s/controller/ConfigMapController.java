package id.my.hendisantika.mongodbk8s.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-mongodb-k8s
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 10/05/25
 * Time: 06.10
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api")
public class ConfigMapController {

    @Value("${application.env.db-host}")
    private String host;

    @Value("${application.env.db-name}")
    private String name;

    @Value("${application.env.debug}")
    private String debug;

    @GetMapping("/db-config")
    public DbInfo getStatus() {
        return new DbInfo(host, name, debug);
    }

    @GetMapping("/db-config-volume")
    public List<String> getVolumeFileContent() {
        String fileName = "/etc/db/app.properties";
        try {
            return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            return Collections.emptyList();
        }
    }
}

record DbInfo(String host, String name, String debug) {
}