package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import management.RDSManagement;
/*
 * Servlet implementation class AcceptRequest
 */
public class AcceptRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RDSManagement rds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestsendName = request.getParameter("friend");
		String requestgetName = request.getParameter("whoacceptfriend");
		rds = new RDSManagement();
		try {
			rds.acceptRequest(requestgetName, requestsendName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addFriends.jsp");
		requestDispatcher.forward(request, response);
	}

}
