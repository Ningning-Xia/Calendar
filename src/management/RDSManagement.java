package management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.Event;
import model.Invitation;

public class RDSManagement {

	//public static String DBurl = "jdbc:mysql://mycalendar.cthu6j2tpw8v.us-east-1.rds.amazonaws.com:3306/mycalendar";
	//public static String DBurl = "jdbc:mysql://localhost:3306/mycalendar";
	public static String DBurl = "jdbc:mysql://judyjava.ccbbwwkvrqk2.us-east-1.rds.amazonaws.com:3306/video";
	public static Connection conn;
	public static Statement st;

	public RDSManagement() {
	}//user

	public static void main(String[] args) {

		System.out.println("Test");

		ArrayList<Event> eventList = new ArrayList<Event>();
	}

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = DBurl;
<<<<<<< HEAD
			//conn = DriverManager.getConnection(url, "root", "123456");
			conn = DriverManager.getConnection(url, "judy", "jj890521");
=======
			conn = DriverManager.getConnection(url, "root", "12345678");
			//conn = DriverManager.getConnection(url, "judy", "jj890521");
>>>>>>> 71b7dc5a9d743d5e04a30f6e1606405f5105c5a9

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
	
	public ArrayList<ArrayList<String>> searchByUname(String username) throws SQLException{
		ArrayList<ArrayList<String>> userinfo = null;
		ResultSet res = null;
		try{
			conn = getConnection();
			userinfo = new ArrayList<ArrayList<String>>();
			ArrayList<String> record = null; 
			String sql = "select email from User where userName = '"+username+"'";
			System.out.println(sql);
			st = (Statement) conn.createStatement();
			//pre.setString(1, username);
			res = st.executeQuery(sql);
			while(res.next()){
				record = new ArrayList<String>();
				String emailaddr = res.getString("email");
				record.add(username);
				record.add(emailaddr);
				userinfo.add(record);
			}
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally {
			try{
				st.close();
				conn.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return userinfo;
	}
	
	public int getUidByName(String uname) throws SQLException{
		int uid = -1;
		try{
			 conn = getConnection();
			 String sql = "select uid from User where userName = '"+uname+"'";
			 st = (Statement)conn.createStatement();
			 ResultSet res = st.executeQuery(sql);
			 while(res.next())
				 uid = res.getInt("uid");
			 
		}catch (Exception e){
			System.out.println(e.getMessage());
		}/*finally {
			st.close();
			conn.close();
		}*/
		return uid;
	}
	
	
	public boolean sendFriendRequest(String sendName,String receiveName) throws SQLException{
		System.out.println("Here");
		int sendid, friendid;
		String sql1, sql2,sql3;
		int status = 1;
		ResultSet res = null;
		
		try{
			conn = getConnection();
			sendid = getUidByName(sendName);
			friendid = getUidByName(receiveName);
			sql1 = "select * from friend where uid1 = " + sendid + " and uid2 = " + friendid;
			System.out.println(sql1);
			st = (Statement)conn.createStatement();
			res = st.executeQuery(sql1);
			if(res.next()){
				sql2 = "update friend set states = " + status + " where uid1 = " + sendid + 
						" and uid2 = " + friendid;
				st = (Statement)conn.createStatement();
				System.out.println(sql2);
				st.executeUpdate(sql2);
			}
			else{
			res = null;
			int count = 0;
			sql3 = "select count(*) from friend";
			st = (Statement)conn.createStatement();
			res = st.executeQuery(sql3);
			if(res.next())
				count = res.getInt(1);
			System.out.println(count);
			count = count + 1;
			
			//res = null;
			sql3 = "insert into friend value(" + count + "," + sendid + "," 
				 + friendid + "," + status + ")";
			st = (Statement)conn.createStatement();
			st.executeUpdate(sql3);
			}
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally{
			st.close();
			conn.close();
		}
		return true;
	}
	
	
	
	public void getFriendList(ArrayList<Integer> fid, ArrayList<String> fname,String username,
		                      int status) throws SQLException{
		/*status = 1 means friend request, status = 2 means friend list.*/
		String sql = null;
		ResultSet res = null;
		try{
			int userid = getUidByName(username);
			if(status == 1){
				sql = "select friend.uid1, User.userName"+
			          " from User, friend where friend.uid2 = " + userid +
			          " and friend.states = " + status +
			          " and friend.uid1 = User.uid";
			}
			else if(status == 2){
				sql = "select User.uid,User.userName from User" + 
			         " where User.uid in " + 
					"(select friend.uid1 from friend where friend.uid2 = "
			         + userid + " and friend.states = " + status +
			         " union " +
			         "select friend.uid2 from friend where friend.uid1 = " 
			         + userid + " and friend.states = " + status + ")";  
			}
			st = (Statement)conn.createStatement();
			res = st.executeQuery(sql);
			while(res.next()){
				int currentid = 0;
				String currentName = null;
				if(status == 1)
					currentid = res.getInt("uid1");
				else if(status == 2)
					currentid = res.getInt("uid");
				currentName = res.getString("userName");
				fid.add(currentid);
				fname.add(currentName);
			}
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally{
			st.close();
			conn.close();
		}
		
	}
	
	public boolean DeleteFriends(String userName, String deleteName) throws SQLException{
		int userId = getUidByName(userName);
		int friendId = getUidByName(deleteName);
		int result = 0;
		try{
			conn = getConnection();
			String sql = "update friend set states = 3 where uid1 = " +userId + 
					     " and uid2 = " + friendId;
			st = (Statement)conn.createStatement();
			result = st.executeUpdate(sql);
			if(result == 0){
				sql = "update friend set states = 3 where uid1 = " + friendId +
					  " and uid2 = " + userId;
				st = (Statement)conn.createStatement();
				result = st.executeUpdate(sql);
				if(result == 0)
					return false;
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally{
			st.close();
			conn.close();
		}
		return true;
	}
	
	public void acceptRequest(String requestgetName,String requestsendName) throws SQLException{
		//acceptRequest(requestgetName, requestsendName);
		int sendid, friendid;
		String sql1, sql2,sql3;
		int status = 2;
		ResultSet res = null;
		
		try{
			conn = getConnection();
			sendid = getUidByName(requestgetName);
			friendid = getUidByName(requestsendName);
			System.out.println(sendid + " " + friendid);
			sql1 = "select * from friend where uid1 = " + friendid + " and uid2 = " + sendid;
			System.out.println(sql1);
			st = (Statement)conn.createStatement();
			res = st.executeQuery(sql1);
			if(res.next()){
				sql2 = "update friend set states = " + status + " where uid1 = " + friendid + 
						" and uid2 = " + sendid;
				st = (Statement)conn.createStatement();
				System.out.println(sql2);
				st.executeUpdate(sql2);
			}
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		}finally{
			st.close();
			conn.close();
		}
	}
	
	public static void updateEmailList(int eid, String emailList) {
		try {
			conn = getConnection();
			
			st = (Statement) conn.createStatement();

			String sql = "update EmailList SET emails ='" + emailList + "' where eid =" + eid;
			
			System.out.println(sql);
			int count = st.executeUpdate(sql);

			System.out.println("Updated emaillist of event " + eid);

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
				e.printStackTrace();
			}
		}

		return eventList;
	}
	
	
}
