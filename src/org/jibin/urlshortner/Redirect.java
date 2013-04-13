package org.jibin.urlshortner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jibin.urlshortner.ShortenURL;

/**
 * Servlet implementation class Redirect
 */
@WebServlet("/Redirect")
public class Redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Redirect() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String code = request.getPathInfo();
		System.out.println("code: " + code);
		String longUrl = null;
		if (code != null && !"".equals(code)) {
			try {
				longUrl = new ShortenURL().getLongUrl(code);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (longUrl == null) {
			System.out.println("longUrl not found.Redirecting to index.jsp");
			try{
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			}catch(Exception e){
				e.printStackTrace();
			}
		} else {
			System.out.println("longUrl found.Redirecting to " + longUrl);
			try{
			response.sendRedirect(longUrl);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
