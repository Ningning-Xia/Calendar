package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import table.invitationTable;

/**
 * Servlet implementation class UpdateInvitationServlet
 */
public class UpdateInvitationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInvitationServlet() {
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
		int eid =  Integer.parseInt(request.getParameter("eid"));
		int uid =  Integer.parseInt(request.getParameter("uid"));
		int action = Integer.parseInt(request.getParameter("status"));
		
		System.out.println(eid + " " + uid + " " + action);
		
		invitationTable.updateInvitation(eid, uid, action);
		RequestDispatcher view = request.getRequestDispatcher("listInvitationServlet");
		view.forward(request,response);
	}

}
