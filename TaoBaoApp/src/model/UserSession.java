package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UserSession implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private List<String> car;
	public UserSession(){
		car = new LinkedList<String>();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<String> getCar() {
		return car;
	}
	public void setCar(List<String> car) {
		this.car = car;
	}
}
