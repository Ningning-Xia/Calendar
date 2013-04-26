package model;

public class Email {
	String toAddr;
	String fromAddr;
	String subject;
	String content;
	
	public Email(){};
	
	public Email(String toAddr, String fromAddr, String subject, String content){
		this.toAddr = toAddr;
		this.fromAddr = fromAddr;
		this.subject = subject;
		this.content = content;
	}
	
	public String getFromAddr(){
		return this.fromAddr;
	}
	
	public String getToAddr(){
		return this.toAddr;
	}
	
	public void setFromAddr(String faddr){
		this.fromAddr = faddr;
	}
	
	public void setToAddr(String taddr){
		this.toAddr = taddr;
	}
	
	public String getBody(){
		return this.content;
	}
	
	public void setBody(String nbody){
		this.content = nbody;
	}
	
	public String getSub(){
		return this.subject;
	}
	
	public void setSub(String sub){
		this.subject = sub;
	}
	
	
}
