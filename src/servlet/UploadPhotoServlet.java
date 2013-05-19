package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import management.S3Class;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import table.eventTable;
import table.photoTable;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

/**
 * Servlet implementation class UploadPhotoServlet
 */
public class UploadPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String bucketName = "ningxia91-bucket";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadPhotoServlet() {
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
		S3Class s3_obj = new S3Class();

		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		if (isMulti) {
			System.out.println("1");
			ServletFileUpload upload = new ServletFileUpload();

			try {
				System.out.println("2");
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					InputStream inputStream = item.openStream();
					if (item.isFormField()) {

					} else {
						System.out.println("3");
						String fileName = item.getName();
						System.out.println(fileName);
						String type = item.getContentType();
						System.out.println("Filename: " + fileName);
						System.out.println("Type: " + type);

						String tmpName;
						if (fileName.contains("\\")) {
							String pattern = Pattern.quote(System
									.getProperty("file.separator"));
							String[] splittedFileName = fileName.split(pattern);
							tmpName = splittedFileName[splittedFileName.length - 1];
						} else {
							tmpName = fileName;
						}

						String[] fileInfo = type.split("/");
						System.out.println(fileInfo[0]);

						/*
						 * if (!fileInfo[0].equals("video")) { String wrongType
						 * =
						 * "Sorry, please upload the file with right file type."
						 * ; request.setAttribute("wrongType", wrongType); }
						 * else
						 */
						if (fileName != null && fileName.length() > 0) {
							System.out.println("4");

							File tempFile = File
									.createTempFile(tmpName, ".tmp");
							tempFile.deleteOnExit();
							/*
							 * FileOutputStream fout = null;
							 * 
							 * try { fout = new FileOutputStream(tempFile); int
							 * c;
							 * 
							 * while ((c = inputStream.read()) != -1) {
							 * fout.write(c); } } catch (Exception e) {
							 * System.err.println(e); } finally { if
							 * (inputStream != null) { inputStream.close(); } if
							 * (fout != null) { fout.close(); } }
							 */

							OutputStream outputStream = null;
							try {
								System.out.println("5");
								outputStream = new FileOutputStream(tempFile);
								System.out.println("6");
								IOUtils.copy(inputStream, outputStream);
							} catch (IOException e) {
								System.err.println(e);
							} finally {
								if (inputStream != null) {
									inputStream.close();
								}
								if (outputStream != null) {
									outputStream.close();
								}
							}
							System.out.println("7");

							PutObjectRequest putObj = new PutObjectRequest(
									bucketName, tmpName, tempFile);
							System.out.println("8");

							// making the object Public
							putObj.setCannedAcl(CannedAccessControlList.PublicRead);
							System.out.println("9");
							s3_obj.s3.putObject(putObj);

							System.out.println(request.getParameter("eid"));
							int eid = Integer.parseInt(request.getParameter("eid"));

							System.out.println("eid " + eid + " filename " + fileName);
							photoTable.insertOnePhoto(eid, fileName);

							System.out.println("Successfully uploaded.");
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		RequestDispatcher view = request.getRequestDispatcher("ListPhotoServlet");
		view.forward(request, response);
	}

}
