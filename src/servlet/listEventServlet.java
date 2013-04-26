package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import management.RDSManagement;
import model.Event;

/**
 * Servlet implementation class listEventServlet
 */
public class listEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,	response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		
		//int uid = Integer.parseInt(request.getParameter("uid"));
		int uid = 1;
		ArrayList<Event> eventList = new ArrayList<Event>();
		RDSManagement rds = new RDSManagement();
		eventList = rds.getEventsByTime(uid);
		request.setAttribute("eventList", eventList);
		RequestDispatcher view = request.getRequestDispatcher("ManageEvents.jsp");
		view.forward(request, response);
		
	}

}
