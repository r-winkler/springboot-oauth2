package ch.renewinkler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer
public class AuthAndResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthAndResourceServerApplication.class, args);
    }

    @RequestMapping("/hello")
    @PreAuthorize("#oauth2.hasScope('read')")
    public Greeting hello() {
        return new Greeting(1l, "Hello from resource server");
    }


}
