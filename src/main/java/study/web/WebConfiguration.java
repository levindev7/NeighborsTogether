package study.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@Import(RepositoryRestMvcConfiguration.class) //конфигурация уже создана внутри спринга, нужно сделать импорт, она делает обычные репозитории РЕСТ репозиториями
public class WebConfiguration {
    @Bean //этот bean самим не нужно autowired, это делается внутри spring
    public ViewResolver jspViewResolver () {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver("/pages/", ".jsp");  // в параметрах префикс и суфикс добавляются к имени возвращаемому контроллером для создания адреса нахождения jsp

        resolver.setViewClass((JstlView.class));

        return resolver;
    }
}
