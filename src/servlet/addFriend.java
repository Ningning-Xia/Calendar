package servlet;

import management.RDSManagement;
import java.io.IOException;
import java.sql.SQLException;

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
		//System.out.println(friendrequest);
		String ownname = "test1";
		//int status = 1;
		boolean requestStatus = false;
		rds = new RDSManagement();
		try {
			requestStatus = rds.sendFriendRequest(ownname, friendrequest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
