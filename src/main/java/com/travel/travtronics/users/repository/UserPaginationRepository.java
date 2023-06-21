package com.travel.travtronics.users.repository;

import java.util.List;

import com.travel.travtronics.dto.UserSearchRequestDto;
import com.travel.travtronics.dto.UserSearchResponse;

public interface UserPaginationRepository {

//	Page<User> fetchPagination(Long organizationId, String userName, Pageable pageable, String sortBy, SortType sortType);

List<UserSearchResponse> findBySearchParameters(UserSearchRequestDto model);
	
	
	

}
