package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addEventServlet
 */
public class addEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addEventServlet() {
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
		
		String event_name = request.getParameter("event_name");
		Date start_time = new Date(request.getParameter("start_time"));
		Date end_time = new Date(request.getParameter("end_time"));
		String location = request.getParameter("location");
		String pic_URL = request.getParameter("pic_URL");
		String video_URL = request.getParameter("video_URL");
		String description = request.getParameter("description");
		
		ArrayList<String> invited_list = new ArrayList<String>();
		String stringList = request.getParameter("invited_list");
		
		if(stringList != null) {
			String str_list[] = stringList.split(";");
			for (String str : str_list) {
				invited_list.add(str);
			}
		}
		
	}

}
