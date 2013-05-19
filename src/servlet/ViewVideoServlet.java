package servlet;

import java.io.IOException;
import management.CloudFront;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewVideoServlet
 */
public class ViewVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewVideoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String key = request.getParameter("key");
		String eid = request.getParameter("eid");
		System.out.println("key" + key);
		System.out.println("eid" + eid);
		
		
		if (eid != null) {
			request.setAttribute("eid", eid);
		}
		
		if (key == null) {
			System.out.println("null key");
		}
		else if (key.contains("/")) {
			String[] keys = key.split("/");
			System.out.println(keys[0]);
			key=keys[0];
			
		}
		
		CloudFront cf = new CloudFront();
		String stream = "\"rtmp://"+ cf.getStreaming()+"/cfx/st/";
		System.out.println("Streaming " + stream);
		
		String download = "\"https://" + cf.getDownload()+ "/";
		System.out.println("Download " + download);
		
		//String stream = "\"rtmp://s2grxoofh5fhn9.cloudfront.net/cfx/st/";
		//String download = "\"https://d2cp5upu02ye7s.cloudfront.net/";
		// get the cloudfront distribution

		System.out.println("View file "+key);
		request.setAttribute("view", key);
		request.setAttribute("stream", stream);
		request.setAttribute("download", download);

		RequestDispatcher view = request.getRequestDispatcher("ListVideoServlet");
		view.forward(request, response);
	}

}
