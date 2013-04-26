package model;

import java.util.ArrayList;
 
public class Event {

	int eid;
	int uid;
	String event_name;
	String start_time;
	String end_time;
	String location;
	String pic_URL;
	String video_URL;
	String description;
	String emailList;
	
	int privacy;
	ArrayList<ArrayList<String>> users;
	
	
	
	public Event(int eid, int uid, String event_name, String start_time,
			String end_time, String location, String pic_URL, String video_URL,
			String description, int privacy, ArrayList<ArrayList<String>> users) {
		this.eid = eid;
		this.uid = uid;
		this.event_name = event_name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.location = location;
		this.pic_URL = pic_URL;
		this.video_URL = video_URL;
		this.description = description;
		this.privacy = privacy;
		this.users = users;
	}
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPic_URL() {
		return pic_URL;
	}
	public void setPic_URL(String pic_URL) {
		this.pic_URL = pic_URL;
	}
	public String getVideo_URL() {
		return video_URL;
	}
	public void setVideo_URL(String video_URL) {
		this.video_URL = video_URL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrivacy() {
		return privacy;
	}
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	public ArrayList<ArrayList<String>> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<ArrayList<String>> users) {
		this.users = users;
	}
	public String getEmailList() {
		return emailList;
	}
	public void setEmailList(String emailList) {
		this.emailList = emailList;
	}

	
}
