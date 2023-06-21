package com.travel.travtronics.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PageView {

	@Value("#{target.id}")
	Integer getId();

	@Value("#{target.page_key}")
	String getPageKey();

	@Value("#{target.page_name}")
	String getPageName();
	
	@Value("#{target.organization_Id}")
	Integer getOrganizationId();

}
