package servlet;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import table.eventTable;

import management.RDSManagement;
import model.Event;

/**
 * Servlet implementation class DeleteEventServlet
 */
public class DeleteEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEventServlet() {
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
		int key = Integer.parseInt(request.getParameter("key"));
		
		//S3Class s3_obj = new S3Class();
		//s3_obj.s3.deleteObject(bucketName, key);
		
		RDSManagement rds = new RDSManagement();
		int uid = eventTable.getUidByEid(key);
		System.out.println("Uid: "+uid);
		eventTable.deleteEventById(key);
		ArrayList<Event> eventList = new ArrayList<Event>();

		eventList = eventTable.getEventsByTime(uid);

		request.getSession().setAttribute("eventList", eventList);
		RequestDispatcher view = request.getRequestDispatcher("/listEventServlet");
		view.forward(request, response);
	}

}
