package org.jibin.urlshortner;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jibin.urlshortner.ShortenURL;

/**
 * Servlet implementation class RequestHandler
 */
@WebServlet("/RequestHandler")
public class RequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String longURL = request.getParameter("longUrl");
		String serverName = request.getServerName();
		int port = request.getServerPort();
		String contextPath = request.getContextPath();
		String shortUrl = null;
		PrintWriter writer = response.getWriter();
		try{
			shortUrl = new ShortenURL().getShort(serverName, port, contextPath, longURL);
		}catch(Exception e){
			e.printStackTrace();
		}
		writer.print(shortUrl);
	}

}
