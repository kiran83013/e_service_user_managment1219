package com.travel.travtronics.user.entity;

public class IdentityProviderModel {
	private String identityProvider;
	private String userId;
	private String userName;

	public IdentityProviderModel() {
		super();
	}

	public IdentityProviderModel(String identityProvider, String userId, String userName) {
		super();
		this.identityProvider = identityProvider;
		this.userId = userId;
		this.userName = userName;
	}

	public String getIdentityProvider() {
		return identityProvider;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setIdentityProvider(String identityProvider) {
		this.identityProvider = identityProvider;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
