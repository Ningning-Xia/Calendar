package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import model.Event;
import model.Invitation;

public class RDSManagement {

	// public static String DBurl =
	//public static String DBurl = "jdbc:mysql://mycalendar.cthu6j2tpw8v.us-east-1.rds.amazonaws.com:3306/mycalendar";
	public static String DBurl = "jdbc:mysql://localhost:3306/mycalendar";
	public static Connection conn;
	public static Statement st;

	public RDSManagement() {
	}

	public static void main(String[] args) {

		System.out.println("Test");
//
//		ArrayList<Integer> emailList = new ArrayList<Integer>();
//		emailList.add(1);
//		emailList.add(2);
//
//		addInvitation(1, emailList);		

		//ArrayList<Event> eventList = new ArrayList<Event>();
		Event event = getEventByName("test2_Event");
		

		getInvitationByUid(1);
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

	public static int addEvent(int uid, String event_name, String start_time,
			String end_time, String location, String pic_URL, String video_URL,
			String description, int privacy) {
		int newEid = 0;
		try {
			conn = getConnection();
			String findMaxEidSql = "select MAX(eid) maxEid from Event;";
			st = (Statement) conn.createStatement();

			int maxEid = 0;
			ResultSet rs = st.executeQuery(findMaxEidSql);
			while (rs.next()) {
				if (rs.getString("maxEid") != null) {
				maxEid = Integer.parseInt(rs.getString("maxEid"));
				}
			}
			newEid = maxEid + 1;

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
		return newEid;
		
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
	
	public static void addInvitation(int eid, ArrayList<Integer> userList) {
		try {
			conn = getConnection();
			st = (Statement) conn.createStatement();

			for (int uid : userList) {
				String searchSql = "select * from Invitation where eid = " + eid 
						+ " and uid = " + uid + ";";
				System.out.println(searchSql);
				ResultSet rs = st.executeQuery(searchSql);
				if (rs.next()){
					System.out.println("Duplicated Invitation");
					continue;
				}
				
				String sql = "insert into Invitation value(" + eid 
						+ "," + uid + "," + 0  + ");";
				
				System.out.println(sql);
				st.executeUpdate(sql);
				System.out.println("Inserted uid: " + uid + " eid " + eid + "  into Invitation");
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
	}
	
	public static ArrayList<Invitation> getInvitationByUid(int uid) {
		ArrayList<Invitation> invitationList = new ArrayList<Invitation>();
		try {
			conn = getConnection();
			st = (Statement) conn.createStatement();
			
			String sql = "select i.eid, e.ename, i.uid, u.username, i.action" +
					" from Invitation i, Event e, User u where i.uid = u.uid and" +
					" e.eid = i.eid and u.uid = " + uid + ";";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int eid = Integer.parseInt(rs.getString("eid"));
				String ename = rs.getString("ename");
				String uname = rs.getString("username");
				int action = Integer.parseInt(rs.getString("action"));
				Invitation invitation = new Invitation(eid, ename, uid, uname, action);
				System.out.println("eid " +eid + " uid " + uid + " action " + action);
				invitationList.add(invitation);
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
		return invitationList;
	}
	
	public static void addEmailList(int eid, String emailList) {
		try {
			conn = getConnection();
			String findMaxEmidSql = "select MAX(emid) maxEmid from EmailList;";
			st = (Statement) conn.createStatement();

			int maxEmid = 0;
			ResultSet rs = st.executeQuery(findMaxEmidSql);
			while (rs.next()) {
				if (rs.getString("maxEmid") != null) {
					maxEmid = Integer.parseInt(rs.getString("maxEmid"));
				}
			}
			int newEmid = maxEmid + 1;

			String sql = "insert into EmailList value(" + newEmid + "," + eid
					+ ", \'" + emailList + "\');";
			
			System.out.println(sql);
			int count = st.executeUpdate(sql);

			System.out.println("Inserted " + count + " items into EmailList");

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
	
	
	
	public static Event getEventByName(String ename) {
		conn = getConnection();
		Event event = null;
		try {
			ArrayList<ArrayList<String>> uidList = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < 4; i++){
				uidList.add(new ArrayList<String>());
			}
			String sql = "select * from event where ename = '" + ename + "' ";
			System.out.println("Select event by name  " + ename);

			st = (Statement) conn.createStatement();
			String startTime, endTime, location, pic, video, description;
			int eid, uid, privacy;
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				eid = Integer.parseInt(rs.getString("eid"));
				uid = Integer.parseInt(rs.getString("uid"));
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

				event = new Event(eid, uid, ename, startTime, endTime,
						location, pic, video, description, privacy, uidList);
				
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

		return event;
	}

	
	
	
	
	
	
	

}
