package cn.vp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.err.println("---进入拦截器--");
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        System.err.println("loginUser是"+loginUser);
        if (loginUser==null){
            //采取转发
            request.getRequestDispatcher("/login.html").forward(request,response);
//            response.sendRedirect("/index.html");
            return false;
        }else {
            //判断跳转的路径是否是Controller中的  @RequestMapping("/login.htm")
            //如果是 的话 ：表单重复提交了
            /*String requestURI = request.getRequestURI();
            System.err.println("---"+requestURI);
            if ("login.htm".equals(requestURI)){
                request.getRequestDispatcher("/list.html").forward(request,response);
                return  false;
            }*/
            return  true;//放行
        }



    }






    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {



    }
}
