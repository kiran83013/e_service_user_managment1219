package com.travel.travtronics.user.entity;

public class Credentials {
	
	private boolean temporary;
	private String type;
	private String value;
	
	public Credentials() {
		super();
	}

	public Credentials(String type, String value, boolean temporary) {
		super();
		this.type = type;
		this.value = value;
		this.temporary = temporary;
	}
	
	public boolean isTemporary() {
		return temporary;
	}
	public void setTemporary(boolean temporary) {
		this.temporary = temporary;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
