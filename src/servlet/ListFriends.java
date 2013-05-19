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
 * Servlet implementation class ListFriends
 */
public class ListFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RDSManagement rds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListFriends() {
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
		String userName = request.getParameter("listfriendname");
		System.out.println(userName);
		rds = new RDSManagement();
		ArrayList<Integer> friendsId = new ArrayList<Integer>();
		ArrayList<String> friendsName = new ArrayList<String>();
		int status = 2;
		try {
			rds.getFriendList(friendsId, friendsName, userName,status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("friendsid", friendsId);
		request.setAttribute("friendsName", friendsName);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addFriends.jsp");
		requestDispatcher.forward(request, response);
	}

}
