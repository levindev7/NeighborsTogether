package study.web;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component//
public class RestRepoConfiguration implements RepositoryRestConfigurer { //нужно сделать отдельным компонентом чтобы репо были не в корневом каталоге

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("/api/repos");
    }
}
