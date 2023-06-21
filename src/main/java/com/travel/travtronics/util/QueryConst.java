package com.travel.travtronics.util;

public class QueryConst {

	public static final String LIST_QUERY = "SELECT * FROM user_info WHERE 1 = 1";

	public static final String ROLE_QUERY = "SELECT \n" + 
			"  role.id, \n" + 
			"  role.role_key, \n" + 
			"  role.role_name, \n" +  
			"  role.landing_page_id page_id, \n" + 
			"  page.page_key, \n" + 
			"  page.path page_path, \n" + 
			"  page.application_id, \n" + 
			"  app.url, \n" + 
			"  app.application_name, \n" + 
			"  role.menu_id \n" + 
			"FROM \n" + 
			"  user_role role \n" + 
			"  LEFT JOIN user_page page ON page.id = role.landing_page_id \n" + 
			"  LEFT JOIN user_application app ON app.id = page.application_id \n" + 
			"WHERE \n" + 
			"  role.id =:roleId";

	public static final String Page_Permission_Query = "SELECT\r\n" + "permission.permission_key\r\n" + "FROM\r\n"
			+ "user_management.user_permission permission\r\n"
			+ "JOIN user_management.user_role_permission role_permission_rel ON permission.id=role_permission_rel.permission_id AND role_permission_rel.is_active=0\r\n"
			+ "JOIN user_management.user_role role ON role.id=role_permission_rel.role_id \r\n"
			+ "JOIN user_management.user_page page ON page.id=permission.page_id\r\n" + "WHERE\r\n"
			+ "role.role_key=?1 AND page.page_key=?2\r\n" + "AND permission.suspendedon IS NULL";

	public static final String Page_Permission_Key_Query = "SELECT\r\n" + "permission.permission_key\r\n" + "FROM\r\n"
			+ "user_management.user_permission permission\r\n"
			+ "JOIN user_management.user_role_permission role_permission_rel ON permission.id=role_permission_rel.permission_id AND role_permission_rel.is_active=0\r\n"
			+ "JOIN user_management.user_role role ON role.id=role_permission_rel.role_id \r\n"
			+ "JOIN user_management.user_page page ON page.id=permission.page_id\r\n" + "WHERE\r\n"
			+ "role.role_key=?1 AND page.page_key=?2 AND permission.permission_key=?3\r\n"
			+ "AND permission.suspendedon IS NULL";

	public static final String Get_Customer_Users = "SELECT eserv_user.user_id, eserv_user.email,\r\n" + 
			"eserv_user.phone_number,eserv_user.record_status,\r\n" + 
			"eserv_user.first_name,eserv_user.last_name,eserv_user.role_id,eserv_role.role_name,eserv_user.organization,mo.Name AS organizationName\r\n" + 
			"FROM user_info eserv_user \r\n" + 
			"JOIN user_role eserv_role ON eserv_user.role_id=eserv_role.id\r\n" + 
			"LEFT JOIN e_services.master_organization mo ON mo.OrganizationId = eserv_user.organization\r\n" + 
			"WHERE eserv_user.biz_id=?1 AND eserv_user.organization=?2";

	public static final String Get_User_Details = "SELECT \n" + 
			"  bu.businessUnitId, \n" + 
			"  bu.Name AS businessUnit, \n" + 
			"  org.Name AS org, \n" + 
			"  org.OrganizationId AS organizationId, \n" + 
			"  dept.Name AS dept, \n" + 
			"  dept.DepartmentId AS deptId, \n" + 
			"  costcenter.Name AS costCenter, \n" + 
			"  costcenter.CostcenterId AS costCenterId, \n" + 
			"  location.Name AS locationInfo, \n" + 
			"  location.LocationId AS locationId, \n" + 
			"  cu.`BusinessName` AS bizName, \n" + 
			"  CONCAT(\n" + 
			"    contact.`FirstName`, '', contact.`LastName`\n" + 
			"  ) AS contactName \n" + 
			"FROM \n" + 
			"  user_management.user_info userInfo \n" + 
			"  LEFT JOIN e_services.business_unit bu ON bu.BusinessUnitId = userInfo.business_unit \n" + 
			"  LEFT JOIN e_services.master_organization org ON org.OrganizationId = userInfo.organization \n" + 
			"  LEFT JOIN e_services.master_departments dept ON dept.DepartmentId = userInfo.department \n" + 
			"  LEFT JOIN e_services.cost_center costcenter ON costcenter.CostcenterId = userInfo.cost_center \n" + 
			"  LEFT JOIN e_services.location location ON location.LocationId = userInfo.location \n" + 
			"  LEFT JOIN e_services.customer_contact contact ON contact.`ID` = userInfo.`contact_id` \n" + 
			"  LEFT JOIN `e_services`.`customer_info` cu ON cu.`CUSTOMERID` = userInfo.`biz_id` \n" + 
			"WHERE \n" + 
			"  userInfo.user_id = :userId";

	/*public static final String Get_Page_Permissions = "SELECT permission.id,\r\n"
			+ "       permission.permission_key,\r\n" + "       permission.permission_name,\r\n" + "       (CASE\r\n"
			+ "            WHEN relation.role_id IS NOT NULL THEN 1\r\n" + "            ELSE 0\r\n"
			+ "        END) permission_flag,\r\n" + "       permission.page_id,\r\n" + "       page.page_name\r\n"
			+ "FROM user_permission permission\r\n"
			+ "LEFT JOIN user_role_permission relation ON relation.permission_id=permission.id\r\n"
			+ "AND relation.role_id=\r\n" + "  (SELECT `id`\r\n" + "   FROM user_role\r\n"
			+ "   WHERE `role_key`= :roleKey)\r\n" + "AND relation.`is_active`=0\r\n"
			+ "LEFT JOIN user_page page ON page.id=permission.page_id\r\n" + "WHERE 1=1\r\n"
			+ "  AND page.`page_key` IN (:pages)";*/
	
	public static final String Get_Page_Permissions = "SELECT permission.id,\r\n" + 
			"permission.permission_key,permission.permission_name, (CASE\r\n" + 
			"   WHEN relation.role_id IS NOT NULL THEN 1 ELSE 0\r\n" + 
			" END) permission_flag, permission.page_id,page.page_name, permission.organization_id AS organizationId\r\n" + 
			"FROM user_permission permission\r\n" + 
			"LEFT JOIN user_role_permission relation ON relation.permission_id=permission.id\r\n" + 
			"AND relation.role_id= (SELECT id FROM user_role\r\n" + 
			" WHERE role_key= :roleKey) AND relation.`is_active`=0\r\n" + 
			"LEFT JOIN user_page page ON page.id=permission.page_id\r\n" + 
			" WHERE 1=1\r\n" + 
			" AND page.page_key IN (:pages)";

	/*public static final String Get_Role_Permission_Keys = "SELECT permission.id,\r\n"
			+ "       permission.permission_key,\r\n" + "       permission.permission_name, permission.organization_id AS organizationId,\r\n" + "       (CASE\r\n"
			+ "            WHEN relation.role_id IS NOT NULL THEN 1\r\n" + "            ELSE 0\r\n"
			+ "        END) permission_flag,\r\n" + "       permission.page_id,\r\n" + "       page.page_name\r\n"
			+ "FROM user_role role\r\n" + "JOIN user_menu_lines menu ON menu.header_id=role.menu_id\r\n"
			+ "JOIN user_page page ON page.id=menu.link\r\n"
			+ "LEFT JOIN user_permission permission ON permission.page_id=page.id\r\n"
			+ "LEFT JOIN user_role_permission relation ON relation.permission_id=permission.id\r\n"
			+ "LEFT JOIN e_services.master_organization mo ON mo.OrganizationId =permission.organization_id\r\n" + 
			"\r\n"
			+ "AND relation.role_id=role.id\r\n" + "AND relation.is_active=0\r\n" + "WHERE 1=1\r\n"
			+ "  AND role.role_key= :roleKey\r\n" + "GROUP BY permission.id, permission.permission_key, permission.permission_name, permission.page_id, page.page_name,permission.organization_id, relation.role_id";*/
	public static final String Get_Role_Permission_Keys=" SELECT permission.id,\r\n" + 
			"permission.permission_key,\r\n" + 
			"permission.permission_name,\r\n" + 
			"(CASE WHEN relation.role_id IS NOT NULL THEN 1 ELSE 0 END) permission_flag,\r\n" + 
			"permission.page_id,\r\n" + 
			"pages.page_name\r\n" + 
			"FROM user_role roles\r\n" + 
			"JOIN user_menu_lines menu ON menu.header_id=roles.menu_id\r\n" + 
			"JOIN user_page pages ON pages.id=menu.link\r\n" + 
			"LEFT JOIN user_permission permission ON permission.page_id=pages.id\r\n" + 
			"LEFT JOIN user_role_permission relation ON relation.permission_id=permission.id\r\n" + 
			"AND relation.role_id=roles.id AND relation.is_active=0\r\n" + 
			"WHERE roles.role_key= :roleKey\r\n" + 
			"GROUP BY permission.id, permission.permission_key, permission.permission_name,  permission.page_id, pages.page_name, relation.role_id";

	public static final String RoleBack_Page_Permissions_Query = "SELECT relation.*\r\n"
			+ "FROM user_role_permission relation\r\n"
			+ "JOIN user_permission permission ON permission.id=relation.permission_id\r\n"
			+ "AND permission.page_id=:pageId\r\n" + "WHERE relation.is_active=0\r\n"
			+ "  AND relation.role_id=:roleId\r\n" + "";

	public static final String Get_Menu_Page_Keys = "SELECT\r\n" + "page.id,page.`page_key`,page.`page_name`,page.organization_Id\r\n"
			+ "FROM \r\n" + "user_role role \r\n" + "JOIN user_menu_lines menu ON menu.header_id=role.menu_id\r\n"
			+ "JOIN user_page page ON page.id=menu.link\r\n" + "WHERE\r\n" + "role.id=?1\r\n" + "GROUP BY page.`id`,page.`page_key`, page.`page_name`";

	public static final String Find_All_OrganizationId_And_UserName = "SELECT\r\n" + 
			"ui.user_id AS userId, \r\n" + 
			"ui.business_unit AS businessUnit,\r\n" + 
			"bu.Name AS businessUnitName,\r\n" + 
			"ui.cost_center  AS costCenter,\r\n" + 
			"cc.Name AS costCenterName,\r\n" + 
			"ui.created_date AS createdDate,\r\n" + 
			"ui.department AS department,\r\n" + 
			"md.Name AS departmentName,\r\n" + 
			"ui.email,\r\n" + 
			"ui.first_name AS firstName, \r\n" + 
			"ui.iam_id AS iamId,\r\n" + 
			"ui.last_name AS lastName, \r\n" + 
			"ui.location AS location,\r\n" + 
			"loc.Name AS locationName, \r\n" + 
			"ui.organization AS organizationId, \r\n" + 
			"mo.Name AS organizationName,\r\n" + 
			"ui.phone_number AS phoneNumber,\r\n" + 
			"ui.ref_id AS refId,\r\n" + 
			"ui.ref_type_id AS refTypeId,\r\n" + 
			"ui.ref_type_name AS refTypeName,\r\n" + 
			"ui.role, \r\n" + 
			"ui.social_id AS socialId,  \r\n" + 
			"ui.social_image_url AS socialImageURL,\r\n" + 
			"ui.social_type AS socialType,\r\n" + 
			"ui.start_date AS startDate,\r\n" + 
			"ui.supervisor,\r\n" + 
			"ui.user_name AS userName, \r\n" + 
			"ui.credit_limit_amount AS creditLimitAmount,\r\n" + 
			"ui.credit_limit_created_date AS creditLimitCreatedDate,\r\n" + 
			"ui.last_update_on_credit_limit AS lastUpdateOnCreditLimit, \r\n" + 
			"ui.updated_date AS updatedDate,\r\n" + 
			"ui.biz_id AS bizId,\r\n" + 
			"ui.contact_id AS contactId,\r\n" + 
			"\r\n" + 
			"CONCAT(\r\n" + 
			"contact.FirstName, ' ', contact.LastName \r\n" + 
			" ) AS contactName,\r\n" + 
			" \r\n" + 
			"ui.wt_id AS wtId,\r\n" + 
			"ui.wt_profile AS wtProfile,\r\n" + 
			"ui.wt_flag AS wtFlag,\r\n" + 
			"ui.role_id AS roleId, \r\n" + 
			"ur.role_name AS roleName, \r\n" + 
			"ui.record_status AS recordStatus \r\n" + 
			"FROM \r\n" + 
			"user_management.user_info ui \r\n" + 
			"LEFT JOIN e_services.business_unit bu ON bu.BusinessUnitId = ui.business_unit\r\n" + 
			"LEFT JOIN e_services.master_organization mo ON mo.OrganizationId = ui.organization \r\n" + 
			"LEFT JOIN e_services.master_departments md ON md.DepartmentId = ui.department\r\n" + 
			"LEFT JOIN e_services.location loc ON loc.LocationId = ui.location \r\n" + 
			"LEFT JOIN e_services.cost_center cc ON cc.CostCenterId = ui.cost_center \r\n" + 
			"LEFT JOIN `user_management`.user_role ur ON ur.id = ui.role_id \r\n" + 
			"LEFT JOIN e_services.customer_contact contact ON contact.ID = ui.contact_id \r\n" + 
			"WHERE \r\n" + 
			"(\r\n" + 
			"ui.organization = ?1\r\n" + 
			"OR 0 = ?1\r\n" + 
			" ) \r\n" + 
			"AND\r\n" + 
			"(ui.user_name = '?2'\r\n" + 
			" OR 'NULL' =  '?2'\r\n" + 
			" )";

	public static final String Find_All_OrganizationId_And_UserName_List_Count = "SELECT \r\n" + 
			" COUNT(*)\r\n" + 
			"FROM \r\n" + 
			"  user_management.user_info ui \r\n" + 
			"  LEFT JOIN e_services.business_unit bu ON bu.BusinessUnitId = ui.business_unit \r\n" + 
			"  LEFT JOIN e_services.master_organization mo ON mo.OrganizationId = ui.organization \r\n" + 
			"  LEFT JOIN e_services.master_departments md ON md.DepartmentId = ui.department \r\n" + 
			"  LEFT JOIN e_services.location loc ON loc.LocationId = ui.location \r\n" + 
			"  LEFT JOIN e_services.cost_center cc ON cc.CostCenterId = ui.cost_center \r\n" + 
			"  LEFT JOIN `user_management`.user_role ur ON ur.id = ui.role_id \r\n" + 
			"  LEFT JOIN e_services.customer_contact contact ON contact.ID = ui.contact_id \r\n" + 
			"WHERE \r\n" + 
			"(\r\n" + 
			"ui.organization = '?1'\r\n" + 
			" OR 0 = ?1\r\n" + 
			" ) \r\n" + 
			" AND\r\n" + 
			" (ui.user_name = '?2'\r\n" + 
			" OR 'NULL' =  '?2'\r\n" + 
			" )";
}
