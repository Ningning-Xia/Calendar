package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import model.Event;

public class RDSManagement {

	//public static String DBurl = "jdbc:mysql://mycalendar.cthu6j2tpw8v.us-east-1.rds.amazonaws.com:3306/mycalendar";
	public static String DBurl = "jdbc:mysql://localhost:3306/mycalendar";
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
	
	public static ArrayList<Event> getEventsByTime(int uid) {
		conn = getConnection();
		ArrayList<Event> eventList = new ArrayList<Event>();
		try {
			ArrayList<ArrayList<String>> uidList = new ArrayList<ArrayList<String>>();
			String sql = "select * from event where uid = " + uid + " order by e.eid DESC";
			System.out.println("Select all events for user" + uid +" sorted by create time");
			
			st = (Statement) conn.createStatement();
			String ename, startTime, endTime, location, pic, video,	description;
			int eid, privacy;
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				eid = Integer.parseInt(rs.getString("eid"));
				ename = rs.getString("ename");
				startTime = rs.getString("starTime");
				endTime = rs.getString("endTime");
				location = rs.getString("location");
				description = rs.getString("description");
				video = rs.getString("video");
				pic = rs.getString("pic");
				privacy = Integer.parseInt(rs.getString("privacy"));
				
				sql = "select distinct uid, action from invitation where eid = " + eid;
				st = (Statement) conn.createStatement();
				ResultSet rs_tmp = st.executeQuery(sql);
				while (rs_tmp.next()){
					int uid_tmp = Integer.parseInt(rs.getString("uid"));
					int action = Integer.parseInt(rs.getString("action"));
					uidList.get(action).add(findNameByUid(uid_tmp));						
				}	

				
				Event event = new Event(eid, uid, ename, startTime, endTime,
						location, pic, video, description, privacy);
				eventList.add(event);
				System.out.println("Event :id " + eid + " name " + ename + " start time " + startTime);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return eventList;
	}
	
	public static String findNameByUid(int uid){
		return "";
	}

}
