package Hi.servlet.web.frontcontroller.v5.adapter;

import Hi.servlet.web.frontcontroller.ModelView;
import Hi.servlet.web.frontcontroller.v3.ControllerV3;
import Hi.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3); // 오 컨트롤러를 가져왔다
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse res, Object handler) throws ServletException, IOException {

        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(req);
        // handler를 호출해주고 반환 타입을 model 타입이 맞춰서 반환
        // 즉 v4, v3 등 다양한 조건의 인터페이스를 가져와 쓸 수 있다

        ModelView mv = controller.process(paramMap);
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
