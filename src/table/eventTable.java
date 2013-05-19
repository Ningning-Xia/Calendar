package table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import management.RDSManagement;
import model.Event;

public class eventTable {

	private static Connection conn;
	private static Statement st;

	public static int addEvent(int uid, String event_name, String start_time,
			String end_time, String location, String pic_URL, String video_URL,
			String description, int privacy) {
		int newEid = 0;
		try {
			conn = RDSManagement.getConnection();
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
	
	public static Event getEventByName(String ename, int mode) {
		// mode 0 for show details (need username & action)
		// mode 1 for edit event (need email list)
		conn = RDSManagement.getConnection();
		Event event = null;
		try {
			ArrayList<ArrayList<String>> uidList = new ArrayList<ArrayList<String>>();
			
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
				String emailList ="";
				if (mode ==0){
				for (int i = 0; i < 4; i++){
					uidList.add(new ArrayList<String>());
				}
				sql = "select userName, action from invitation i JOIN User u on i.uid = u.uid where i.eid = "
						+ eid;
				st = (Statement) conn.createStatement();
				ResultSet rs_tmp = st.executeQuery(sql);
				while (rs_tmp.next()) {
					String userName = rs_tmp.getString("userName");
					int action = Integer.parseInt(rs_tmp.getString("action"));
					uidList.get(action).add(userName);
					System.out.println("Event id: "+eid+" user: "+userName + " action: " + action);
				}
				} else {
					sql = "select emails from emaillist where eid = "+eid;
					st = (Statement) conn.createStatement();
					ResultSet rs_tmp = st.executeQuery(sql);
					
					while (rs_tmp.next()){
						emailList = rs_tmp.getString("emails");
						
					}
					System.out.println("Email list for event "+eid+" is "+ emailList);
					
				}
				event = new Event(eid, uid, ename, startTime, endTime,
						location, pic, video, description, privacy, uidList);
				event.setEmailList(emailList);
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

	
	public static void deleteEventById(int eid){
		conn = RDSManagement.getConnection();
		//ArrayList<Video> videoList = new ArrayList<Video>();
		try {
			String sql = "delete from event where eid = " + eid ;
			System.out.println(sql);
			st = (Statement) conn.createStatement();
			int count = st.executeUpdate(sql);
			System.out.println("Deleted event " + eid);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void updateEvent(int eid, int uid, String event_name, String start_time,
			String end_time, String location, String pic_URL, String video_URL,
			String description, int privacy) {
		
		try {
			conn = RDSManagement.getConnection();
			String sql = "UPDATE event SET uid="+uid+", ename='"+event_name+"', startTime='"
			+start_time+"', endTime='"+ end_time+"', location='"+location+"', description='"
			+description+"', video='"+video_URL+"', pic='"+pic_URL+"', privacy='"
			+privacy+"' WHERE eid=" +eid;


			System.out.println(sql);
			st = (Statement) conn.createStatement();
			int count = st.executeUpdate(sql);

			System.out.println("Updated event " + eid);

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
	public static int getUidByEid(int eid){
		int uid=0;
		try{
			conn = RDSManagement.getConnection();
			String sql = "Select uid from Event where eid = " + eid + ";";
			System.out.println(sql);
			st = (Statement) conn.createStatement();
			ResultSet rs_tmp = st.executeQuery(sql);
			while (rs_tmp.next()) {
				uid = Integer.parseInt(rs_tmp.getString("uid"));
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
		return uid;
	}
	
	public static String getVideoNameByEid(int eid) {
		String video = null;
		try {
			conn = RDSManagement.getConnection();
			String sql = "Select video from Event where eid = " + eid + ";";
			System.out.println(sql);
			st = (Statement) conn.createStatement();
			ResultSet rs_tmp = st.executeQuery(sql);
			while (rs_tmp.next()) {
				video = rs_tmp.getString("video");
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
		return video;

	}
	
	public static void updateEventVideo(int eid, String videoName) {
		
		try {
			conn = RDSManagement.getConnection();
			String sql = "UPDATE event SET video='"+videoName+"' where eid =" + eid +";";
			System.out.println(sql);
			st = (Statement) conn.createStatement();
			int count = st.executeUpdate(sql);

			System.out.println("Updated event " + eid + " add video " + videoName);

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
		conn = RDSManagement.getConnection();
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
				//System.out.println("event id: "+ eid);
				sql = "select userName, action from invitation i JOIN User u on i.uid = u.uid where i.eid = "
						+ eid;
				st = (Statement) conn.createStatement();
				ResultSet rs_tmp = st.executeQuery(sql);
				while (rs_tmp.next()) {
					String userName = rs_tmp.getString("userName");
					int action = Integer.parseInt(rs_tmp.getString("action"));
					uidList.get(action).add(userName);
					//System.out.println("Event id: "+eid+" user: "+userName + " action: " + action);
				}

				Event event = new Event(eid, uid, ename, startTime, endTime,
						location, pic, video, description, privacy, uidList);
				eventList.add(event);
				/*System.out.println("Event :id " + eid + " name " + ename
						+ " start time " + startTime);*/
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

}
