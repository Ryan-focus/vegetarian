package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AutType
 */
/**"
  * 雿輻Annotation雿輻��蕪�
  * @WebFilter摰���� javax.servlet.FilterAPI 摰儔���蕪�
  * filterName摰����蕪����迂
  * urlPatterns�����蕪��RL璅∪��,銋雿輻撅祆�听alue靘恐���.(urlPattern撅祆�找�摰���)
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
		// ���� session��
		HttpSession session = req.getSession();
		// ��� session ��甇下ser��������
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
