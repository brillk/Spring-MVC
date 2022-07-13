package Hi.servlet.web.frontcontroller.v5;

import Hi.servlet.web.frontcontroller.ModelView;
import Hi.servlet.web.frontcontroller.MyView;
import Hi.servlet.web.frontcontroller.v3.ControllerV3;
import Hi.servlet.web.frontcontroller.v3.FrontControllerServletV3;
import Hi.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import Hi.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import Hi.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import Hi.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name ="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private Map<String, Object> handlerMappingMap = new HashMap<>();
    private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();

        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //MemberFormControllerV3
        Object handler = getHandler(req);

        if(handler == null) {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //ControllerV3HandlerAdapter
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        //모델 뷰 반환
        ModelView mv = adapter.handle(req, res, handler);

        String viewName = mv.getViewName();// 논리 이름 new-form

        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), req,res);
    }

    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {

        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다");
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
