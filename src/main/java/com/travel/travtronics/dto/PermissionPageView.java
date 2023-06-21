package com.travel.travtronics.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PermissionPageView {

	@Value("#{target.id}")
	Integer getId();

	@Value("#{target.permission_key}")
	String getPermissionKey();

	@Value("#{target.permission_name}")
	String getPermissionName();

	@Value("#{target.permission_flag}")
	Integer getPermissionFlag();

	@Value("#{target.page_id}")
	Integer getPageId();

	@Value("#{target.page_name}")
	String getPageName();

}
