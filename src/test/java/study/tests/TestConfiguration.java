package study.tests;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import study.web.ProdConfiguration;
import study.web.WebConfiguration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = {"study.web", "study.db"},
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {ProdConfiguration.class, WebConfiguration.class} //отключение веба
                ))
public class TestConfiguration {
    @Bean
    public EntityManagerFactory getEntityManagerFactory () {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }
}
