package model;

public class Invitation {
	
	int eid;
	String ename;
	int uid;
	String uname;
	int action;
	
	public Invitation(int eid, String ename, int uid, String uname, int action) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.uid = uid;
		this.uname = uname;
		this.action = action;
	}
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	
	
}
