package com.travel.travtronics.dto;

import org.springframework.beans.factory.annotation.Value;

public interface UserCustomerView {

	@Value("#{target.user_id}")
	Integer getUserId();

	@Value("#{target.email}")
	String getEmail();

	@Value("#{target.phone_number}")
	String getPhoneNumber();

	@Value("#{target.record_status}")
	String getStatus();

	@Value("#{target.first_name + ' ' + target.last_name}")
	String getFullName();

	@Value("#{target.role_id}")
	Integer getRoleId();

	@Value("#{target.role_name}")
	String getRole();
	
	@Value("#{target.organization}")
	Integer getOrganizationId();
	
	
}
