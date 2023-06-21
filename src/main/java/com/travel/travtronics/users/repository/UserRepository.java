package com.travel.travtronics.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.travel.travtronics.dto.UserCustomerView;
import com.travel.travtronics.users.model.User;
import com.travel.travtronics.util.QueryConst;
import com.travel.travtronics.util.SortType;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	Optional<User> findByPhoneNumber(String phoneNumber);

	@Query("select user from User user where user.email=?1 or user.userName=?1")
	Optional<User> findByEmail(String email);

	Boolean existsByEmailIgnoreCase(String mail);

	Boolean existsByUserNameIgnoreCase(String userName);

	Optional<User> findByUserIdAndIamIdIsNotNull(Long userId);

	Optional<User> findByUserId(Long userId);

	Optional<User> findByEmailAndIamIdIsNotNull(String email);

	List<User> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String firstName, String Lastname);

	@Query(value = QueryConst.Get_User_Details, nativeQuery = true)
	UserExtraIntoDto getUserInfoDeatils(@Param("userId") Long userId);

	@Modifying
	@Transactional
	@Query("Update User u SET u.iamId=:iamId WHERE u.userId=:userId")
	public void updateIamId(@Param("iamId") String iamId, @Param("userId") Long userId);

//	@Query(value = QueryConst.Get_Customer_Users, nativeQuery = true)
//	List<UserCustomerView> findByCustomerId(Long customerId);

	List<User> findByOrganizationId(Long organizationId);

	@Query(value = QueryConst.Get_Customer_Users, nativeQuery = true)
	List<UserCustomerView> findByCustomerIdAndOrganizationId(Long customerId, Long organizationId);

	List<User> findByUserNameContainingIgnoreCase(String name);
	
	@Query(value = QueryConst.Find_All_OrganizationId_And_UserName,countQuery = QueryConst.Find_All_OrganizationId_And_UserName_List_Count, nativeQuery = true)
	Page<User> findByOrganizationIdAndUserName(Long organizationId, String userName, Pageable pageable, String sortBy,
			SortType sortType);

	

}
