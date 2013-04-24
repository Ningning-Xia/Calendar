package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RDSManagement {

	public static String DBurl = "jdbc:mysql://mycalendar.cthu6j2tpw8v.us-east-1.rds.amazonaws.com:3306/mycalendar";
	public static Connection conn;
	public static Statement st;

	public RDSManagement() {

	}

	public static void main(String[] args) {
		System.out.println("Test");
		addEvent(4, "test4_Event", "04-13-2013 9:00 ", "04-13-2013 10:00 ", "Columbia University" , 
				"Description", null, null, 1);
	}

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = DBurl;
			conn = DriverManager.getConnection(url, "admin", "12345678");

			if (conn != null) {
				System.out.println("get datasource succeed!");
			}

			if (conn == null) {
				throw new Exception("get datasource failed!");
			}

		} catch (SQLException e) {
			System.out.println("Access failed " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Failed " + e.getMessage());
		}
		return conn;
	}

	public static void addEvent(int uid, String event_name, String start_time,
			String end_time, String location, String pic_URL, String video_URL,
			String description, int privacy) {
		try {
			conn = getConnection();
			String findMaxEidSql = "select MAX(eid) maxEid from Event;";
			st = (Statement) conn.createStatement();

			int maxEid = 0;
			ResultSet rs = st.executeQuery(findMaxEidSql);
			while (rs.next()) {
				maxEid = Integer.parseInt(rs.getString("maxEid"));
			}
			int newEid = maxEid + 1;

			String sql = "insert into Event value(" + newEid + "," + uid + ", \'" + event_name
					+ "\',\'" + start_time + "\',\'" + end_time + "\',\'" 
					+ location + "\',\'" + pic_URL + "\',\'"+ video_URL
					+ "\',\'" + description + "\'," + privacy + ");";
			
			System.out.println(sql);

			int count = st.executeUpdate(sql);
			
			System.out.println("Inserted " + count + " items into Event");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
