package servlet;

import model.Email;
import management.EmailManage;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendEmails
 */
public class SendEmails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Email email;
	public EmailManage emanage;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmails() {
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
		String fromAddr = request.getParameter("fromUserEmail");
		String toAddr = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("body");
		System.out.println("Should be: ");
		System.out.println(fromAddr+ " " + toAddr + " " + subject + " " + content + " ");
		email = new Email(fromAddr, toAddr, subject,content);
		emanage = new EmailManage();
		emanage.sendEmail(email);
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/addFriends.jsp");
		requestDispatcher.forward(request, response);
	}

}
