package study.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
public class WebConfiguration {
    @Bean //этот bean самим не нужно autowired, это делается внутри spring
    public ViewResolver jspViewResolver () {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver("/pages/", ".jsp");  // в параметрах префикс и суфикс добавляются к имени возвращаемому контроллером для создания адреса нахождения jsp

        resolver.setViewClass((JstlView.class));

        return resolver;
    }
}
