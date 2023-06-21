package com.travel.travtronics.users.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.dto.PageView;
import com.travel.travtronics.users.model.PagesModel;
import com.travel.travtronics.util.QueryConst;

public interface PagesRepository extends JpaRepository<PagesModel, Long> {

	Optional<PagesModel> findByPageId(Long pageId);

	@Query(value = "SELECT p.id AS pageId, a.id AS applicationId,a.application_name AS applicationName, p.page_name AS pageName, p.path AS path, p.created_by AS createdBy, p.updated_by AS updatedBy,\r\n" + 
			"p.created_date AS createdDate, p.updated_date AS updatedDate, p.status AS status,p.page_key AS pageKey,p.organization_id AS organizationId, mo.Name AS organizationName FROM user_management.user_page p\r\n" + 
			"LEFT JOIN user_management.user_application a ON a.id = p.application_id\r\n" + 
			"LEFT JOIN e_services.master_organization mo ON mo.OrganizationId = p.organization_id WHERE p.application_id=?1", nativeQuery = true)
	List<Map<String, String>> findAllByList(Long applicationId);

	@Query(value = "SELECT p.id AS pageId, a.id AS applicationId,a.application_name AS applicationName, p.page_name AS pageName, p.path AS path, p.created_by AS createdBy, p.updated_by AS updatedBy,\r\n" + 
			"p.created_date AS createdDate, p.updated_date AS updatedDate, p.status AS status,p.page_key AS pageKey,p.organization_id AS organizationId, mo.Name AS organizationName FROM user_management.user_page p\r\n" + 
			"LEFT JOIN user_management.user_application a ON a.id = p.application_id\r\n" + 
			"LEFT JOIN e_services.master_organization mo ON mo.OrganizationId = p.organization_id WHERE p.organization_id=?1", nativeQuery = true)
	List<Map<String, String>> findAllListByOrganizationId(Long organizationId);

//	@Query(value = "SELECT p.id AS pageId, a.id AS applicationId,a.application_name AS applicationName, p.page_name AS pageName, p.path AS path, p.created_by AS createdBy, p.updated_by AS updatedBy, \r\n"
//			+ "p.created_date AS createdDate, p.updated_date AS updatedDate, p.status AS status FROM user_management.user_page p \r\n"
//			+ "LEFT JOIN user_management.user_application a ON a.id = p.application_id\r\n"
//			+ "WHERE p.page_name LIKE %?1%", nativeQuery = true)
//	List<Map<String, String>> findByGroupNameContainsIgnoreCase(String pageName);

	List<PagesModel> findByPageNameIgnoreCaseEquals(String pageName);

	List<PagesModel> findByPageKeyIgnoreCaseEquals(String pageKey);

	@Query(value = QueryConst.Get_Menu_Page_Keys, nativeQuery = true)
	List<PageView> findAllPageKeysByRoleId(Integer roleId);

	

}
