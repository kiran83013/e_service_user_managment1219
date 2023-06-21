package com.travel.travtronics.user.entity;

public class KeycloakUserModel {

	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String role;
	private Long userId;
	private String appRoled;
	private String userName;
	
	

	public KeycloakUserModel(String email, String firstName, String lastName, String password, String userName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userName = userName;
	}

	public KeycloakUserModel(String email, String firstName, String lastName, String password, String role,
			String userName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
		this.userName = userName;
	}

	public KeycloakUserModel(String email, String firstName, String lastName, String password, Long userId,
			String appRoleId, String userName) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userId = userId;
		this.appRoled = appRoleId;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppRoled() {
		return appRoled;
	}

	public void setAppRoled(String appRoled) {
		this.appRoled = appRoled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
