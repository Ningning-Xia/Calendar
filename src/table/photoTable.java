package table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import management.RDSManagement;
import model.Photo;

public class photoTable {
	private static Connection conn;
	private static Statement st;
	
	public static void main(String[] args) {
		//insertOnePhoto(1, "photo1");
		deleteOnePhoto(4);
	}
	
	public static int getMaxPID() {
		int maxId = 0;
		try {
			conn = RDSManagement.getConnection();
			st = (Statement) conn.createStatement();
			String sql = "SELECT MAX(pid) maxid FROM Photo;";
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
	
	public static int insertOnePhoto(int eid, String pname) {
		int pid = getMaxPID()+1;
		try {
			conn = RDSManagement.getConnection();
			st = (Statement) conn.createStatement();
			String sql = "insert into Photo values(" + eid + "," + pid +",'" + pname + "');";  
			System.out.println(sql);
			int count = st.executeUpdate(sql);
			System.out.println("Inserted " + count + " item into Photo");

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
		return pid;	
	}
	
	public static void deleteOnePhoto(int pid) {
		try {
			conn = RDSManagement.getConnection();
			st = (Statement) conn.createStatement();
			String sql = "delete from  Photo where pid =" + pid + ";";  
			System.out.println(sql);
			int count = st.executeUpdate(sql);
			System.out.println("Deleted " + count + " item into Photo");

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
	
	public static ArrayList<Photo> getPhotosByEid(int eid) {
		ArrayList<Photo> photoList = new ArrayList<Photo>();
		try {
			conn = RDSManagement.getConnection();
			st = (Statement) conn.createStatement();
			String sql = "select eid, pid, pname from Photo where eid =" + eid + ";";
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int pid = Integer.parseInt(rs.getString("pid"));
				String pname = rs.getString("pname");
				Photo photo = new Photo(eid, pid, pname);
				photoList.add(photo);
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
		return photoList;
	}

}
