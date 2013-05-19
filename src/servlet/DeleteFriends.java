package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import management.RDSManagement;
/**
 * Servlet implementation class DeleteFriends
 */
public class DeleteFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RDSManagement rds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFriends() {
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
		String deleteName = request.getParameter("friend");
		String userName = request.getParameter("whodeletefriend");
		System.out.println(userName + deleteName);
		boolean isDeleted = false;
		rds = new RDSManagement();
		try {
			isDeleted = rds.DeleteFriends(userName, deleteName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isDeleted)
			System.out.println("Already");
		else
			System.out.println("Failure");
		request.setAttribute("Deleted", isDeleted);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addFriends.jsp");
		requestDispatcher.forward(request, response);
	}

}
