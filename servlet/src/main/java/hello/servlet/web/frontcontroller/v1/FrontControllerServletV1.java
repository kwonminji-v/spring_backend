package hello.servlet.web.frontcontroller.v1;


import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(name = "frontControllerServletV1", urlPatterns ="/front-controller/v1/*" ) //*: v1 하위에 어떤것이 들어오든 해당 메서드가 호출
public class FrontControllerServletV1 extends HttpServlet {

    /** 매핑정보 만들기
     * -> Map<url, Controller>로 매핑 정보를 만듬
     * 어떤 url을 호출하게되면 ContollerV1을 호출 */
    private Map<String ,ControllerV1> controllerMap = new HashMap<>();

    /** 매핑 정보를 생성할 때 미리 담기
     * 기본 생성자에 아래와 같이 put 메서드를 작성하면 서블릿이 생성될 때 해당 해당 패턴들에 미리 저장을 함*/
    public FrontControllerServletV1() {
            controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
            controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
            controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServletV1.service");

        //url 경로를 얻을 수 있습니다.예를들면, /front-controller/v1/members/new-form 해당 부분이 requestURI에 담김
        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMap.get(requestURI); //어떤 경로가 요청되냐에 따라서 이어진 객체 인스턴스가 반환됨 ex) /front-controller/v1/members/save 가 호출되면, new MemberSaveControllerV1()이게 호출





    }
}
