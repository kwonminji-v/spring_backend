package hello.servlet.web.servletmvc_controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/** 컨트롤러의 역할 */
@WebServlet(name="mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    //mvc 패턴은 컨트롤러를 거쳐 뷰로 전달되기 때문에, memberForm을 보여주고 싶으려면 controller로 요청이 들어와야 함
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String viewPath = "/WEB-INF/views/new-form.jsp"; //해당 jsp 파일로 이동하는 경로를 viewPath로 만들어줌
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//컨트롤러에서 뷰로 이동할때 해당 경로로 이동할 수 있도록 해줌
        dispatcher.forward(request, response); // 다른 서블릿이나 JSP로 이동할 수 있는 기능 , 서버 내부에서 다시 호출이 발생
                           // -> 리다이렉트로 이동하는 것이 아니라 서블릿 호출 -> jsp 호출 -> jsp에서 응답 만든 후 클라이언트에게 전송
    }
}
/**
 * 1. 고객의 요청이 오면 service 메서드가 호출된 후 viewPath의 jsp 경로를 호출함
 * 2. dispatcher.forward(request, response)로 서버 내부의 경로로 다시 호출*/