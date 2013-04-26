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
 * Servlet implementation class ShowEventDetails
 */
public class ShowEventDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowEventDetails() {
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
		Event event = null;
		String ename = request.getParameter("key");
		System.out.println("evnet name is" + ename);
		RDSManagement rds = new RDSManagement();
		event = rds.getEventByName(ename);
		request.setAttribute("event", event);
		if (event != null)
		System.out.println("show event details servlet");
		
		RequestDispatcher view = request.getRequestDispatcher("ShowEventDetails.jsp");
		view.forward(request, response);
	}

}
