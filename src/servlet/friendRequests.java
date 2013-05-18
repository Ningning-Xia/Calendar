package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import management.RDSManagement;


/**
 * Servlet implementation class friendRequests
 */
public class friendRequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RDSManagement rds; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public friendRequests() {
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
		String username = "test1";
		String userName = request.getParameter("whofriendrequest");
		int status = 1;
		ArrayList<Integer> requestsId = new ArrayList<Integer>();
		ArrayList<String> requestsName = new ArrayList<String>();
		rds = new RDSManagement();
		try {
			rds.getFriendList(requestsId, requestsName, userName, status);
			//System.out.println(requestsName.get(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("requestsId", requestsId);
		request.setAttribute("reuqestsName", requestsName);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addFriends.jsp");
		requestDispatcher.forward(request, response);
	}

}
