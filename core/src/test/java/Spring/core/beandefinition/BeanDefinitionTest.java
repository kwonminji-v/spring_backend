package Spring.core.beandefinition;

import Spring.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig .class);
    GenericXmlApplicationContext abc = new GenericXmlApplicationContext("appconfig.xml");


    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = abc.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = abc.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinition = " + beanDefinition + "beanDefinition = " + beanDefinition);
            }
        }
    }
    }
