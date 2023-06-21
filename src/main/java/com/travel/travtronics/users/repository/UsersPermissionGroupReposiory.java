package com.travel.travtronics.users.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.travel.travtronics.users.model.UsersPermissionGroupModel;


public interface UsersPermissionGroupReposiory extends PagingAndSortingRepository<UsersPermissionGroupModel, Integer> {

	UsersPermissionGroupModel findByGroupId(Integer groupId);

	Set<UsersPermissionGroupModel> findByGroupNameContainsIgnoreCase(String gname);

	Boolean existsByGroupName(String groupName);

	List<UsersPermissionGroupModel> findByOrganizationId(Long organizationId);

//	@Query("select group from BePermissionGroupModel group where group.orgId=?1 or 0=?1")
//	Set<BePermissionGroupModel> findAllGroupInfo(Long orgId);

}
