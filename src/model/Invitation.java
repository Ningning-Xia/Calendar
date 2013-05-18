package model;

public class Invitation {
	
	private int eid;
	private String ename;
	private String startTime;
	private String endTime;
	private int uid;
	private String uname;
	private int action;
	
	public Invitation(int eid, String ename, String startTime, String endTime,
			int uid, String uname, int action) {
		this.eid = eid;
		this.ename = ename;
		this.startTime = startTime;
		this.endTime = endTime;
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
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
