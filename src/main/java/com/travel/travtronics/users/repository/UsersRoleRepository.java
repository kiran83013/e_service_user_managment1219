package com.travel.travtronics.users.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.travel.travtronics.dto.RoleDto;
import com.travel.travtronics.users.model.UserRoleModel;

public interface UsersRoleRepository extends PagingAndSortingRepository<UserRoleModel, Long> {

	@Query("select userRole from UserRoleModel userRole where userRole.id=:roleId")
	Optional<UserRoleModel> findByRoleId(@Param("roleId") Long roleId);

	@Query("select role.id from UserRoleModel role where role.name=?1")
	Optional<Long> getRoleIdByRoleName(String name);

	@Query("select role.key from UserRoleModel role where role.id=?1")
	String getRoleKeyByRoleId(Long id);

	Optional<UserRoleModel> findByNameIgnoreCase(String role);

	Optional<UserRoleModel> findByKey(String key);

	Optional<UserRoleModel> findByName(String name);

	Optional<UserRoleModel> findByKeyAndName(String key, String name);

	@Query(nativeQuery = true)
	Optional<RoleDto> findByRoleInfoId(@Param("roleId") Integer roleId);

	Set<UserRoleModel> findByKeyIgnoreCaseEquals(String key);

	List<UserRoleModel> findByOrganizationId(Long organizationId);

}
