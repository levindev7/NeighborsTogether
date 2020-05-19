package study.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory) { //factory из ProdConfig
        return new JpaTransactionManager(factory);
    }
}
