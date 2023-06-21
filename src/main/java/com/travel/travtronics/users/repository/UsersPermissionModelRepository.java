package com.travel.travtronics.users.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.users.model.UsersPermissionModel;
import com.travel.travtronics.util.QueryConst;

public interface UsersPermissionModelRepository extends PagingAndSortingRepository<UsersPermissionModel, Integer> {

	@Query("select permission from UsersPermissionModel permission where permission.id= :permissionId")
	Optional<UsersPermissionModel> findByPermissionId(@Param("permissionId") Integer permissionId);

	Boolean existsByKey(String key);

//	Set<UsersPermissionModel> findByKeyContainsIgnoreCase(String key);

	Set<UsersPermissionModel> findByKeyIgnoreCaseEquals(String key);

	@Query(value = QueryConst.Page_Permission_Query, nativeQuery = true)
	Set<String> getPermisionsByPageAndRole(String roleKey, String pageKey);

	@Query(value = QueryConst.Page_Permission_Key_Query, nativeQuery = true)
	Set<String> getPermisionsByPageAndRoleAndKey(String roleKey, String pageKey, String key);

	List<UsersPermissionModel> findByOrganizationId(Long organizationId);
}
