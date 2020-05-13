package study.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {"study.web", "study.db"})  // показывает путь в котором нужно искать классы
public class ProdConfiguration {

    @Bean
    public EntityManagerFactory getEntityManagerFactory () {
        return Persistence.createEntityManagerFactory("ProdPersistenceUnit");
    }
}
