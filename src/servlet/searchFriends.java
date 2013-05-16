package servlet;

import management.RDSManagement;

import java.sql.SQLException;
import java.util.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class searchFriends
 */
public class searchFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RDSManagement rds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchFriends() {
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
		rds = new RDSManagement();
		ArrayList<ArrayList<String>> inforecords = null;
		String uname = request.getParameter("username");
		try {
			inforecords = rds.searchByUname(uname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("userinfo", inforecords);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addFriends.jsp");
		requestDispatcher.forward(request, response);
	}

}
