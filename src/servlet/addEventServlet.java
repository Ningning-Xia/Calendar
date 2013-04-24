package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import management.RDSManagement;

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
		
		String event_name = request.getParameter("ename");
		String start_date = request.getParameter("start-date");
		String start_time = request.getParameter("start-time");
		String end_date = request.getParameter("end-date");
		String end_time = request.getParameter("end-time");
		String location = request.getParameter("location");
		String pic_URL = request.getParameter("picture");
		String video_URL = request.getParameter("video");
		String description = request.getParameter("description");
		int privacy = Integer.parseInt(request.getParameter("privacy"));
		
		String start = start_date + " " + start_time + ":00";
		String end = end_date + " " +end_time + ":00";
		int uid = 1;
		
		RDSManagement rds = new RDSManagement();
		rds.addEvent(uid, event_name, start, end, location, pic_URL, video_URL, description, privacy);
		
		ArrayList<String> invited_list = new ArrayList<String>();
		String invitelist = request.getParameter("invitelist");
		
		if(invitelist != null) {
			String str_list[] = invitelist.split(";");
			for (String str : str_list) {
				invited_list.add(str);
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/listEventServlet");
		view.forward(request, response);
		
	}

}
