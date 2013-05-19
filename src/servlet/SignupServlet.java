package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import table.userTable;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		
		String account = request.getParameter("account");
		String email = request.getParameter("email");
		String password = request.getParameter("Pass");
		
		if (!userTable.checkByUsername(account)) {
			String duplicate_account = "This username already exists.";
			request.setAttribute("duplicate_account", duplicate_account);
		}
		
		if (!userTable.checkByEmail(email)) {
			String duplicate_email = "This email already exists.";
			request.setAttribute("duplicate_email", duplicate_email);
		}
		
		if (userTable.checkByUsername(account) && userTable.checkByEmail(email)) {
			User user = new User(-1, account, email, password);
			int uid = userTable.insertOneUser(user);
			user.setUid(uid);
			
			request.setAttribute("user",user);
		    request.getSession().setAttribute("user", user);
			RequestDispatcher view = request.getRequestDispatcher("calendar.jsp");
			view.forward(request,response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("Signup.jsp");
			view.forward(request,response);
		}
	}

}
