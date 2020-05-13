package study.web;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Configuration
public class AppConfiguration {

    @Bean   //создание объектов и связывание с методами под @aotowired по типу
    @Qualifier("defaultManager") // чтобы выбрать конкретную реализацию (когда их больше 1), бин и связанный метод помечаются аннотацией @Qualifier и
    public EntityManager getEntityManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    @Bean   //создание объектов и связывание с методами под @aotowired по типу
    @Qualifier("secondManager") // чтобы выбрать конкретную реализацию (когда их больше 1), бин и связанный метод помечаются аннотацией @Qualifier и
    public EntityManager getEntityManagerSecond(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
}
