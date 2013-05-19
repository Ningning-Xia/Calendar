package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.Photo;
import table.photoTable;

/**
 * Servlet implementation class ListPhotoServlet
 */
public class ListPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPhotoServlet() {
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
		String eid = request.getParameter("eid");
		if (eid != null) {
			request.setAttribute("eid", eid);
			int eid_int = Integer.parseInt(eid);
			ArrayList<Photo> photoList = photoTable.getPhotosByEid(eid_int);
			if (photoList != null && photoList.size() > 0) {
				request.setAttribute("photoList", photoList);
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher("Photo.jsp");
		view.forward(request, response);
	}

}
