package com.travel.travtronics.user.entity;

import java.util.List;
import java.util.Map;

public class SignUpIAMModel {
	private String firstName;

	private String lastName;
	private ClientRoles clientRoles;
	private List<Credentials> credentials;
	private String email;
	private boolean emailVerified;
	private boolean enabled;
	private String username;
	private Map<String, Object> attributes;

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
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

	public ClientRoles getClientRoles() {
		return clientRoles;
	}

	public void setClientRoles(ClientRoles clientRoles) {
		this.clientRoles = clientRoles;
	}

	public List<Credentials> getCredentials() {
		return credentials;
	}

	public void setCredentials(List<Credentials> credentials) {
		this.credentials = credentials;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
