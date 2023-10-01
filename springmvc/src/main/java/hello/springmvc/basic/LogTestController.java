package hello.springmvc.basic;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {


    @RequestMapping("log-test")
    public String logTest() {

        String name = "Spring";
        log.info(" info log = {}", name);

        return "ok";
        //return 시 문자를 return 하면 @Controller를 쓰면 view이름을 반환해야하는데
        //@RestController를 사용하면, rest-api의 rest로서 return에 문자열을 반환하면 해당 문자열이 그대로 반환됩니다.
    }
}
