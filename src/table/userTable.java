package table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import management.RDSManagement;
import model.Invitation;
import model.User;

public class userTable {

	private static Connection conn;
	private static Statement st;
	//private RDSManagement rds = new RDSManagement();
	
	public static void main(String[] args) {
		//User user = getUserByUsername("test7");
		//System.out.println("user id: " + user.getUid());
		
		User user1 = new User(7,"test7","test7@gmail.com","123456");
		updateOneUser(user1);
	}
	
	public static User getUserByUsername(String userName) {
		User user = null;
		
		try {
			conn = RDSManagement.getConnection();
			st = (Statement) conn.createStatement();
			String sql = "select * from User where userName = '" + userName + "';";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				user = constructUserFromResultSet(rs);
			}
			//return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("User id " + user.getUid() + " User Name: " + user.getUserName());
		return user;		
	}
	
	public static int insertOneUser(User user) {
		int uid = getMaxUserID()+1;
		String userName = user.getUserName();
		String email = user.getEmail();
		String password = user.getPassword();
		try {
			conn = RDSManagement.getConnection();
			st = (Statement) conn.createStatement();
			String sql = "insert into User values(" + uid + ",'" + userName +"', '" + email + "','" +password +"');"; 
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
		return uid;	
	}
	
	public static int updateOneUser(User user) {
		int uid = user.getUid();
		String userName = user.getUserName();
		String email = user.getEmail();
		String password = user.getPassword();
		try {
			conn = RDSManagement.getConnection();
			st = (Statement) conn.createStatement();
			String sql = "update User set email ='" + email + "', password ='" + password + "' where uid = " + uid + ";"; 
			System.out.println(sql);
			st.executeUpdate(sql);
			System.out.println("Update user: " + uid);

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
	

	public static int getMaxUserID() {
		User user = null;
		int maxId = 0;
		try {
			conn = RDSManagement.getConnection();
			st = (Statement) conn.createStatement();
			String sql = "SELECT MAX(uid) maxid FROM User;";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				maxId = rs.getInt("maxid");
			}
			//return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//int maxId = user.getUid();
		return maxId;		
	}
	
	private static User constructUserFromResultSet(ResultSet rs) {
		try {
			User user = new User();
			user.setUid(Integer.parseInt(rs.getString("uid")));
			user.setUserName(rs.getString("userName"));
			user.setPassword(rs.getString("password"));
			user.setEmail(rs.getString("email"));
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
