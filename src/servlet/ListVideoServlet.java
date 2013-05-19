package servlet;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import management.RDSManagement;
import management.S3Class;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import table.eventTable;

import model.User;
import model.Video;

import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.StringUtils;

/**
 * Servlet implementation class ListServlet
 */
public class ListVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// public static S3Class s3_obj = null;
	// public static String bucketName = "assign2-bucket";
	public static String bucketName = "ningxia91-bucket";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListVideoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
/*		S3Class s3_obj = new S3Class();

		// get object
		String prefix = "/assign2-bucket/";
		// int prefix_size = prefix.length();
		ObjectListing objects = s3_obj.s3.listObjects(bucketName);*/
		String eid = request.getParameter("eid");
		if (eid != null) {
			request.setAttribute("eid", eid);
		}
		//RDSManagement rds = new RDSManagement();
		//User user = (User)request.getSession().getAttribute("user");
		//int uid = user.getUid();
		String video = eventTable.getVideoNameByEid(Integer.parseInt(eid));
		
		if (video != null) {
			request.setAttribute("video", video);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("Video.jsp");
		view.forward(request, response);
	}

}
