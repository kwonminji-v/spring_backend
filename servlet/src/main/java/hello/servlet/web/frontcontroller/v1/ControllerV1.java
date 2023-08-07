package hello.servlet.web.frontcontroller.v1;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**process메서드가 담긴 ControllerV1 메서드를 생성
 * 프론트 컨트롤러는 해당 인터페이스를 호출해서 구현과 관계없이 로직의 일관성을 가질 수 있음*/
public interface ControllerV1 {

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
