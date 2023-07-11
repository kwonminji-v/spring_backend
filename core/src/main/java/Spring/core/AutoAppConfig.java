package Spring.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//컴포넌트 스캔은 @Component 애노테이션이 붙은 클래스를 스캔해서 빈으로 등록합니다.
//컴포넌트 스캔은 자동으로 스프링 빈을 등록해주는데 제외할 파일을 excludeFilters로 적용이 가능

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //Configuration이 Componentscan 기능을 가지고 있기 때문에 Configuration.class는 자동으로 불러와지지 않도록 설정
)
public class AutoAppConfig {


}
