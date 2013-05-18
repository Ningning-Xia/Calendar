package model;

import java.sql.Timestamp;

public class Video {
	public int vid;
	public String vname;
	public double averating;
	public Timestamp timestamp;
	
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public int getVid() {
		return vid;
	}
	public void setVid(int vid) {
		this.vid = vid;
	}
	public double getAverating() {
		return averating;
	}
	public void setAverating(double averating) {
		this.averating = averating;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
