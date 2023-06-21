package com.travel.travtronics.users.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.travel.travtronics.dto.UserSearchRequestDto;
import com.travel.travtronics.dto.UserSearchResponse;


@Component
public class UserPaginationImpl implements UserPaginationRepository{
	
	
	@PersistenceContext
	@Autowired
	EntityManager em;

	
	
//	@Override
//	public Page<User> fetchPagination(Long organizationId, String userName, Pageable pageable, String sortBy,
//			SortType sortType) {
//
//
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<User> createQuery = builder.createQuery(User.class);
//		Root<User> root = createQuery.from(User.class);
//		List<Predicate> predicates = new ArrayList<Predicate>();
//		
//		if (organizationId != null && organizationId != 0) {
//			predicates.add(builder.equal(root.<Long>get("organizationId"), organizationId));
//		}
//		if (userName != null && !userName.isBlank()) {
//
//			predicates.add(builder.like(builder.lower(root.<String>get("userName")),
//					"%" + userName.toLowerCase() + "%"));
//
//		}
//		createQuery.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//		if ((sortType == SortType.asc || Objects.isNull(sortType)) && sortBy != null && !sortBy.isEmpty()) {
//			createQuery.orderBy(builder.asc(root.get(sortBy)));
//		}
//		if (sortType == SortType.desc && sortBy != null && !sortBy.isEmpty()) {
//			createQuery.orderBy(builder.desc(root.get(sortBy)));
//		}
//		List<User> resultList = em.createQuery(createQuery).setFirstResult(Math.toIntExact(pageable.getOffset()))
//				.setMaxResults(pageable.getPageSize()).getResultList();
//		CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
//		Root<User> serviceRootCount = countQuery.from(User.class);
//		countQuery.select(builder.count(serviceRootCount))
//		.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//		Long count = em.createQuery(countQuery).getSingleResult();
//		Page<User> finalList = new PageImpl<>(resultList, pageable, count);
//		return finalList;
//	}



	@Override
	public List<UserSearchResponse> findBySearchParameters(UserSearchRequestDto model) {

		Map<String, Object> params = new HashMap<>();
		StringBuilder sql = new StringBuilder();

		sql.append(
				"SELECT ui.user_id AS userId, ui.business_unit AS businessUnit,bu.Name AS businessUnitName, ui.cost_center AS costCenter,cc.Name AS costCenterName, ui.created_date AS createdDate, ui.department AS department,dept.Name AS departmentName,\r\n"
						+ "ui.email, ui.first_name AS firstName, ui.iam_id AS iamId, ui.last_name AS lastName, ui.location, loc.Name AS locationName, ui.organization AS organizationId,org.Name AS organizationName, ui.phone_number AS phoneNumber,\r\n"
						+ "ui.ref_id AS refId, ui.ref_type_id AS refTypeId, ui.ref_type_name AS refTypeName, ui.role, ui.social_id AS socialId, ui.social_image_url AS socialImageURL,\r\n"
						+ "ui.social_type AS socialType, ui.start_date AS startDate, ui.supervisor, ui.user_name AS userName, ui.credit_limit_amount AS creditLimitAmount,\r\n"
						+ "ui.credit_limit_created_date AS creditLimitCreatedDate, ui.last_update_on_credit_limit AS lastUpdateOnCreditLimit, ui.updated_date AS updatedDate, ui.biz_id AS bizId,\r\n"
						+ "ui.contact_id  AS contactId,CONCAT(contact.FirstName, ' ',contact.LastName) AS contactName, ui.wt_id AS wtId, ui.wt_profile AS wtProfile, ui.wt_flag AS wtFlag, ui.role_id AS roleId,ur.role_name AS roleName, ui.record_status AS recordStatus FROM user_info ui\r\n"
						+ "LEFT JOIN e_services.business_unit bu ON bu.BusinessUnitId=ui.business_unit\r\n"
						+ "LEFT JOIN  e_services.master_organization org ON org.OrganizationId=ui.organization\r\n"
						+ "LEFT JOIN e_services.master_departments dept ON dept.DepartmentId=ui.department\r\n"
						+ "LEFT JOIN e_services.location  loc ON loc.LocationId=ui.location\r\n"
						+ "LEFT JOIN e_services.cost_center cc ON cc.CostCenterId=ui.cost_center\r\n"
						+ "LEFT JOIN `user_management`.user_role ur ON ur.id = ui.role_id\r\n"
						+ "LEFT JOIN e_services.customer_contact contact ON contact.ID = ui.contact_id WHERE 1=1"
						+ System.lineSeparator());

		if (model.getUserId() != null && model.getUserId() != 0) {
			sql.append("AND ui.user_id= :userId" + System.lineSeparator());
			params.put("userId", model.getUserId());
		}

		if (model.getUserName() != null && !model.getUserName().trim().isEmpty()) {
			sql.append("AND ui.user_name= :userName" + System.lineSeparator());
			params.put("userName", model.getUserName());
		}

		if (model.getEmail() != null && !model.getEmail().trim().isEmpty()) {
			sql.append("AND ui.email= :email" + System.lineSeparator());
			params.put("email", model.getEmail());
		}

		if (model.getPhoneNumber() != null && !model.getPhoneNumber().trim().isEmpty()) {
			sql.append("AND ui. ui.phone_number= :phoneNumber" + System.lineSeparator());
			params.put("phoneNumber", model.getPhoneNumber());

		}

		if (model.getDepartment() != null && model.getDepartment().isEmpty()) {
			sql.append("AND  ui.department= :department" + System.lineSeparator());
			params.put("department", model.getDepartment());
		}

		if (model.getLocation() != null && model.getLocation().isEmpty()) {
			sql.append("AND  ui.location= :location" + System.lineSeparator());
			params.put("location", model.getLocation());
		}

		if (model.getOrganizationId() != null && model.getOrganizationId() != 0) {
			sql.append("AND ui.organization= :organizationId" + System.lineSeparator());
			params.put("organizationId", model.getOrganizationId());
		}

		if (model.getBusinessUnit() != null && model.getBusinessUnit().isEmpty()) {
			sql.append("AND ui.business_unit= :businessUnit" + System.lineSeparator());
			params.put("businessUnit", model.getBusinessUnit());
		}

		if (model.getCostCenter() != null && model.getCostCenter().isEmpty()) {
			sql.append("AND ui.cost_center= :costCenter" + System.lineSeparator());
			params.put("costCenter", model.getCostCenter());
		}

		if (model.getRoleId() != null && model.getRoleId() != 0) {
			sql.append("AND ui.role_id = :roleId" + System.lineSeparator());
			params.put("roleId", model.getRoleId());
		}

		Query query = em.createNativeQuery(sql.toString(), "find_by_search_params");

		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());

		}

		@SuppressWarnings("unchecked")
		List<UserSearchResponse> resultList = query.getResultList();

		return resultList;

	}

}
