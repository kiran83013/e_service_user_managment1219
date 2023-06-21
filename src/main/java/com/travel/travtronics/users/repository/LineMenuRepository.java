package com.travel.travtronics.users.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.dto.LineMapDto;
import com.travel.travtronics.users.model.UserLineMenu;

public interface LineMenuRepository extends JpaRepository<UserLineMenu, Integer> {

	ArrayList<UserLineMenu> findByHeaderId(Integer headerId);

	@Transactional
	@Modifying
	@Query("delete from UserLineMenu where headerId = ?1")
	void removeByHeaderId(Integer headerId);

	@Query(value = "SELECT created_by AS createdBy, created_date AS createdDate FROM `user_menu_lines` WHERE `header_id`=?1 ORDER BY id ASC LIMIT 1", nativeQuery = true)
	LineMapDto getUserIdFromHeaderId(Integer headerId);

	Boolean existsByHeaderIdAndName(Integer headerId, String name);

}
