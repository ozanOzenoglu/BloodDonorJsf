package org.kanbankasi.apps;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.facebookapi.FacebookJsonRestClient;

/**
 * Servlet implementation class KanDuyurusuEkle
 */
@WebServlet(
		urlPatterns = { "/KanDuyurusuEkle" }, 
		initParams = { 
				@WebInitParam(name = "API_KEY", value = "331610003541987", description = "API_KEY"), 
				@WebInitParam(name = "SECRET_KEY", value = "be55a1ad2315c03743bd35228d2f0d9c", description = "SECRET_KEY")
		})
public class KanDuyurusuEkle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KanDuyurusuEkle() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String apiKey = getServletConfig().getInitParameter("API_KEY");
		String secretKey = getServletConfig().getInitParameter("SECRET_KEY");
		HttpSession session = request.getSession();
		String sessionKey = (String) session.getAttribute("kanDuyurusuSession");
		String authToken = request.getParameter("auth_token");
		
		FacebookJsonRestClient client = null;
		
		if(sessionKey != null){
			client = new FacebookJsonRestClient(apiKey, secretKey, sessionKey);
		}
		else if(authToken != null){
			client = new FacebookJsonRestClient(apiKey, secretKey);
			//client.setIsDesktop(false);
			try{
				sessionKey = client.auth_getSession(authToken);
				session.setAttribute("kanDuyurusuSession", sessionKey);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			response.sendRedirect("http://www.facebook.com/login.php?api_key=" + apiKey);
			return;
		}
		response.getWriter().println("Hello World!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
