package model;

import java.util.ArrayList;
import java.util.Date;
 
public class Event {

	String event_name;
	Date start_time;
	Date end_time;
	String location;
	String pic_URL;
	String video_URL;
	String description;
	ArrayList<String> invited_list;
	
	public Event(String event_name, Date start_time, Date end_time,
			String location, String pic_URL, String video_URL,
			String description, ArrayList<String> invited_list) {
		this.event_name = event_name;
		this.start_time = start_time;
		this.end_time = end_time;
		this.location = location;
		this.pic_URL = pic_URL;
		this.video_URL = video_URL;
		this.description = description;
		this.invited_list = invited_list;
	}
	
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
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
	public ArrayList<String> getInvited_list() {
		return invited_list;
	}
	public void setInvited_list(ArrayList<String> invited_list) {
		this.invited_list = invited_list;
	}
	
	
}
