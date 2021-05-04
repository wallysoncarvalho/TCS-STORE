package info.wallyson.tcsstore.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"info.wallyson.tcsstore.rest", "info.wallyson.tcsstore.persistence"})
@EnableJpaRepositories("info.wallyson.tcsstore.persistence")
@EntityScan("info.wallyson.tcsstore.persistence")
public class App {

  public static void main(String[] args) {
      SpringApplication.run(App.class, args);
  }
}
