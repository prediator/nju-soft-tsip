package cn.edu.nju.tsip.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ClientLoginInterceptor extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		System.out.println(request.getSession().getId());
		if(request.getRequestURL().indexOf("login")!=-1){
			return true;
		}
		if(request.getSession().getAttribute("id")!=null){
			 return true;
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("{\"status\":\"false\",\"error\":\"没有登录\"}");
			out.flush();
			out.close();
			return false;
		}
	}

}
