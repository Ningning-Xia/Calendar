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
		// TODO Auto-generated method stub
		String username = "test1";
		rds = new RDSManagement();
		//int userid = 1;
		ArrayList<Integer> friendsId = new ArrayList<Integer>();
		ArrayList<String> friendsName = new ArrayList<String>();
		int status = 2;
		try {
			rds.getFriendList(friendsId, friendsName, username,status);
			/*System.out.println(friendsId.get(0));
			System.out.println(friendsName.get(1));*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("friendsid", friendsId);
		request.setAttribute("friendsName", friendsName);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addFriends.jsp");
		requestDispatcher.forward(request, response);
	}

}
