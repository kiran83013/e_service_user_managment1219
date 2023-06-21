package com.travel.travtronics.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travel.travtronics.users.model.UserHeaderMenu;

public interface HeaderMenuRepository extends JpaRepository<UserHeaderMenu, Integer> {

	@Query("select menu from UserHeaderMenu menu where menu.status='Active' and (menu.endDate is null or menu.endDate>=CURDATE()) AND menu.organizationId =?1")
	List<UserHeaderMenu> findAllByOrganizationId(Long organizationId);

}
