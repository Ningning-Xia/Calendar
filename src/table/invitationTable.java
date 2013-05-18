package table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import management.RDSManagement;
import model.Invitation;

public class invitationTable {

	private static Connection conn;
	private static Statement st;
	
	public static ArrayList<Invitation> getInvitationByUid(int uid) {
		ArrayList<Invitation> invitationList = new ArrayList<Invitation>();
		try {
			conn = RDSManagement.getConnection();
			st = (Statement) conn.createStatement();
			
			String sql = "select i.eid, e.ename, e.startTime, e.endTime, i.uid, u.username, i.action" +
					" from Invitation i, Event e, User u where i.uid = u.uid and" +
					" e.eid = i.eid and u.uid = " + uid + ";";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int eid = Integer.parseInt(rs.getString("eid"));
				String ename = rs.getString("ename");
				String startTime = rs.getString("startTime");
				String endTime = rs.getString("endTime");
				String uname = rs.getString("username");
				int action = Integer.parseInt(rs.getString("action"));
				Invitation invitation = new Invitation(eid, ename, startTime, endTime, uid, uname, action);
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
	
	
	public static void updateInvitation(int eid, int uid, int action) {
		try {
			conn = RDSManagement.getConnection();
			st = (Statement) conn.createStatement();
			
			String sql = "update Invitation set action =" + action + " where uid = " + uid + " and eid = " + eid + ";";
			System.out.println(sql);
			st.executeUpdate(sql);
			System.out.println("Update eid: " + eid + " uid: " + uid);

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
