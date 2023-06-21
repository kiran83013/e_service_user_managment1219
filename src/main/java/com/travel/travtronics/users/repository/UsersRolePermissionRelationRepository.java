package com.travel.travtronics.users.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.dto.PermissionPageView;
import com.travel.travtronics.users.model.UsersRolePermissionRelation;
import com.travel.travtronics.util.QueryConst;

public interface UsersRolePermissionRelationRepository extends CrudRepository<UsersRolePermissionRelation, Integer> {

	List<UsersRolePermissionRelation> findByRoleIdIn(List<Integer> roleIds);

//	@Modifying
//	@Transactional
//	@Query(value = "DELETE FROM `user_role_permission` WHERE `permission_id`=?1", nativeQuery = true)
//	int deleteByPermission(Integer permissionId);

	Set<UsersRolePermissionRelation> findByPermissionId(Integer permissionId);

	@Query(value = QueryConst.Get_Page_Permissions, nativeQuery = true)
	List<PermissionPageView> findByRoleIdAndPageId(@Param("pages") Set<String> pages, @Param("roleKey") String roleId);

	@Query(value = QueryConst.RoleBack_Page_Permissions_Query, nativeQuery = true)
	Set<UsersRolePermissionRelation> findByRoleIdAndPageIdAndPermissionId(@Param("roleId") Integer roleId,
			@Param("pageId") Integer pageId);

	@Query(value = QueryConst.Get_Role_Permission_Keys, nativeQuery = true)
	List<PermissionPageView> findPermissionByRoleId(@Param("roleKey") String roleKey);

}
