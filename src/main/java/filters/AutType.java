package filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AutType
 */
/**"
  * 使用Annotation使用過濾器
  * @WebFilter宣告 javax.servlet.FilterAPI 定義為過濾器
  * filterName宣告過濾器的名稱
  * urlPatterns指定要過濾的URL模式,也可使用屬性value來宣告.(urlPattern屬性一定要有)
  */

//@WebFilter(filterName="/AutType",urlPatterns="/*",
//		dispatcherTypes=DispatcherType.FORWARD)
public class AutType implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.setContentType("text/html; charset=UTF-8");
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此User是否登入過】
		Object type = session.getAttribute("user");	
		
		if (type == null) {
			 String url ="/Login";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				return;
		} else {			
			chain.doFilter(request, response);
			return;
		}
	}
	public void destroy() {
		config = null;
	}
}
