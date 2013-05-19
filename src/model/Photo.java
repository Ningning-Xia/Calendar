package model;

public class Photo {
	private int eid;
	private int pid;
	private String pname;
	
	public Photo(int eid, int pid, String pname) {
		super();
		this.eid = eid;
		this.pid = pid;
		this.pname = pname;
	}
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
}
