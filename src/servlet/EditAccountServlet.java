package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import table.userTable;
import model.User;

/**
 * Servlet implementation class EditAccountServlet
 */
public class EditAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAccountServlet() {
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
		//int uid = Integer.parseInt(request.getParameter("uid"));
		String userName = request.getParameter("Account");
		System.out.println(userName);
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		
		User user = userTable.getUserByUsername(userName);
		String message;
		if (user != null) {
			System.out.println("typed Passwd: " + oldPass);
			System.out.println("stored Passwd: " + user.getPassword());
			if (user.getPassword().equals(oldPass)) {
				user.setPassword(newPass);
				userTable.updateOneUser(user);
				request.setAttribute("user", user);
				message = "Updated Password successfully";
				request.setAttribute("message",message);
			}
			else {
				message = "The old Password is not correct";
				request.setAttribute("message",message);
			}

		}
		RequestDispatcher view = request.getRequestDispatcher("UserHome.jsp");
		view.forward(request,response);	
	}
}
