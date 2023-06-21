package com.travel.travtronics.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travel.travtronics.users.model.ApplicationsModel;

public interface ApplicationsRepository extends JpaRepository<ApplicationsModel, Long>{

	Optional<ApplicationsModel> findByApplicationId(Long applicationId);

	List<ApplicationsModel> findByApplicationNameIgnoreCaseEquals(String applicationName);

	List<ApplicationsModel> findByOrganizationId(Long organizationId);

//	@Query(value = "SELECT ua.application_name AS applicationName,ua.id, ua.application_description AS applicationDescription, ua.url AS url, ua.created_by AS createdBy, ua.updated_by AS updatedBy,\r\n" + 
//			"ua.created_date AS createdDate, ua.updated_date AS updatedDate,ua.status AS status\r\n" + 
//			"FROM user_management.user_application ua WHERE ua.application_name LIKE %?1%", nativeQuery = true)
//	List<Map<String, String>> findByGroupNameContainsIgnoreCase(String applicationName);
	

}
