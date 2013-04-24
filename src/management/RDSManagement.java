package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import model.Event;

public class RDSManagement {

	// public static String DBurl =
	// "jdbc:mysql://mycalendar.cthu6j2tpw8v.us-east-1.rds.amazonaws.com:3306/mycalendar";
	public static String DBurl = "jdbc:mysql://localhost:3306/mycalendar";
	public static Connection conn;
	public static Statement st;

	public RDSManagement() {
	}

	public static void main(String[] args) {
		System.out.println("Test");
		//ArrayList<String> emailList = new ArrayList<String>();
		//emailList.add("test1@gmail.com");
		//emailList.add("test9@gmail.com");

		//findUidByEmail(emailList);
		getEventsByTime(2);

	}

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = DBurl;
			//conn = DriverManager.getConnection(url, "admin", "12345678");
			conn = DriverManager.getConnection(url, "root", "12345678");
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

			String sql = "insert into Event value(" + newEid + "," + uid
					+ ", \'" + event_name + "\',\'" + start_time + "\',\'"
					+ end_time + "\',\'" + location + "\',\'" + pic_URL
					+ "\',\'" + video_URL + "\',\'" + description + "\',"
					+ privacy + ");";

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
			for (int i = 0; i < 4; i++){
				uidList.add(new ArrayList<String>());
			}
			String sql = "select * from event where uid = " + uid
					+ " order by eid DESC";
			System.out.println("Select all events for user " + uid
					+ " sorted by create time");

			st = (Statement) conn.createStatement();
			String ename, startTime, endTime, location, pic, video, description;
			int eid, privacy;
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				eid = Integer.parseInt(rs.getString("eid"));
				ename = rs.getString("ename");
				startTime = rs.getString("startTime");
				endTime = rs.getString("endTime");
				location = rs.getString("location");
				description = rs.getString("description");
				video = rs.getString("video");
				pic = rs.getString("pic");
				privacy = Integer.parseInt(rs.getString("privacy"));
				System.out.println("event id: "+ eid);
				sql = "select userName, action from invitation i JOIN user u on i.uid = u.uid where i.eid = "
						+ eid;
				st = (Statement) conn.createStatement();
				ResultSet rs_tmp = st.executeQuery(sql);
				while (rs_tmp.next()) {
					String userName = rs_tmp.getString("userName");
					int action = Integer.parseInt(rs_tmp.getString("action"));
					uidList.get(action).add(userName);
					System.out.println("Event id: "+eid+" user: "+userName + " action: " + action);
				}

				Event event = new Event(eid, uid, ename, startTime, endTime,
						location, pic, video, description, privacy, uidList);
				eventList.add(event);
				System.out.println("Event :id " + eid + " name " + ename
						+ " start time " + startTime);
			}
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

		return eventList;
	}

	public static ArrayList<Integer> findUidByEmail(ArrayList<String> emailList) {
		ArrayList<Integer> uidList = new ArrayList<Integer>();
		try {
			conn = getConnection();
			st = (Statement) conn.createStatement();

			for (String email : emailList) {
				String sql = "select uid from User where email = '" + email
						+ "';";
				ResultSet rs = st.executeQuery(sql);
				int uid;
				if (rs.next()) {
					uid = Integer.parseInt(rs.getString("uid"));
					uidList.add(uid);
					System.out.println("User: id " + uid);
				} else {
					System.out.println("User: " + email
							+ " is not in our system");
				}

			}
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
		return uidList;
	}


}
