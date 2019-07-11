package cn.jwinni.springboot.Interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private List<String> url = new ArrayList();

    /**
     * 开始进入地址请求拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if (1==1){
        //if(session.getAttribute("userToken") != null){
            return true;
        }else{
            response.sendRedirect("login");	//未登录，跳转到登录页
            return false;
        }
    }

    /**
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    /**
     * 定义排除拦截URL
     * @return
     */
    public List<String> getUrl(){
        //方法
        url.add("/login");      //登录页
        url.add("/wx");
        url.add("/wxAffectiveProject");
        url.add("/getVCode");



        //网站静态资源
        url.add("/bootstrap/**");
        url.add("/dist/**");
        url.add("/images/**");
        url.add("/jquery/**");
        url.add("/weui/**");
        return url;
    }
}
