package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    //스프링이 실행될 때, Configuration을 읽고 하단의 코드를 스프링 빈에 등록하라고 인식하여 등록을 진행합니다.
    //멤버 서비스와 멤버 리포지토리를 스프링 빈에 등록해주고, 스프링빈에 등록되어있는 멤버 리포지토리를 멤버 서비스에 넣어줍니다.

    private final MemberRepository memberRepository;
    
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
        //( ) 안에 커서를 두고 ctrl+p를 누르게되면 인자값 즉시 보기 (Parameter Info)를 확인할 수 있습니다.
    }


//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }



//    @Bean
//    public MemberRepository memberRepository() {
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//        return
//    }

}
