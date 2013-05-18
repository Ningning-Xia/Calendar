package servlet;

import management.RDSManagement;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addFriend
 */
public class addFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public RDSManagement rds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addFriend() {
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
		// TODO Auto-generated method stub
		String friendrequest = request.getParameter("friend");
		String ownname = "test1";
		boolean requestStatus = false;
		String ownName = request.getParameter("whofindfriend");
		rds = new RDSManagement();
		System.out.println("Here's the friend what?");
		System.out.println(friendrequest);
		try {
			requestStatus = rds.sendFriendRequest(ownName, friendrequest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addFriends.jsp");
		requestDispatcher.forward(request, response);
	}

}
