package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.travel.travtronics.Userlogin;
import com.travel.travtronics.dto.EmailModel;
import com.travel.travtronics.dto.UserCustomerView;
import com.travel.travtronics.dto.UserSearchRequestDto;
import com.travel.travtronics.dto.UserSearchResponse;
import com.travel.travtronics.response.APIResponsePaging;
import com.travel.travtronics.user.entity.KeycloakUserModel;
import com.travel.travtronics.users.model.User;
import com.travel.travtronics.users.repository.UserPaginationRepository;
import com.travel.travtronics.users.repository.UserRepository;
import com.travel.travtronics.users.repository.UsersRoleRepository;
import com.travel.travtronics.util.SortType;

@Service
public class UserManagementService {

	@Value("${com.travel.url.resetPassword}")
	private String resetPassword;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	UsersRoleRepository beUserRoleRepository;

	@Autowired
	EmailService emailService;

//	@Value("${com.travel.tech.optionaudit.email.resetPassword}")
//	private String resetPassword;

	public Logger logger = LoggerFactory.getLogger(this.getClass());

	private RestTemplate restTemplate;

	public ResponseEntity<?> createUser(User model) {

		try {

			KeycloakUserModel keycloakModel = new KeycloakUserModel(model.getEmail(), model.getFirstName(),
					model.getLastName(), model.getPassword(), model.getUserName());

			ResponseEntity<String> userInfo = authenticationService.createUser(keycloakModel);

			String iamId = userInfo.getBody();
			model.setIamId(iamId);

			User user = userRepository.save(model);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("{\"message\":\"" + e.getLocalizedMessage() + "\"}", HttpStatus.CONFLICT);
		}

	}

	public ResponseEntity<String> createUser(String email, String firstName, String lastName, String password) {
		String url = "http://localhost:9020/login/auth/createUser";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Userlogin userlogin = new Userlogin(email, firstName, lastName, password);
		HttpEntity<Object> entity = new HttpEntity<Object>(userlogin, headers);
		return restTemplate.postForEntity(url, entity, String.class);

	}

	public ResponseEntity<?> updateUser(User model) throws Exception {
		Optional<User> optional = userRepository.findByUserId(model.getUserId());
		if (optional.isPresent()) {
			Date date = new Date();
			model.setUpdatedDate(date);

			User user = userRepository.save(model);

			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else
			return ErrorMessageService.userIdNotFound(model.getUserId());

	}

	public ResponseEntity<?> getUser(Long userId) throws Exception {
		Optional<User> optional = userRepository.findByUserId(userId);
		if (optional.isPresent()) {
//			optional.get().setPassword(encryptService.decryptBody(optional.get().getPassword()));
			return new ResponseEntity<User>(optional.get(), HttpStatus.OK);
		} else
			return ErrorMessageService.userIdNotFound(userId);

	}

	public ResponseEntity<?> getUsersByCustomer(Long customerId, Long organizationId) {
		List<UserCustomerView> userInfo = userRepository.findByCustomerIdAndOrganizationId(customerId, organizationId);
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}

	public ResponseEntity<List<User>> getUsers(Long organizationId) {

		try {
			List<User> list = userRepository.findByOrganizationId(organizationId);
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Autowired
	UserPaginationRepository userPageRepository;

	public APIResponsePaging getPagenationByOrganization(Long organizationId, String userName,int pageNo, int pageSize, String sortBy,
			SortType sortType) {
		
		Pageable pageable = PageRequest.of(pageNo, pageSize,
				sortType == SortType.asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		
		Page<User> userPages = userRepository.findByOrganizationIdAndUserName(organizationId, userName, pageable, sortBy,
				sortType);
//
//	Page<UserDto> pageUserDto = userPages.map(entity -> {
//			
//			UserDto response = ApiConverter.convertDtoToModel(entity);
//			
//			Optional<String> deptInfo = userRepository.getDepartment(response.getDepartment());
//			Optional<String> orgInfo = userRepository.getOrganization(response.getOrganizationId());
//			Optional<String> buInfo = userRepository.getBusinessUnit(response.getBusinessUnit());
//			Optional<String> locInfo = userRepository.getLocation(response.getLocation());
//			Optional<String> contactInfo = userRepository.getContact(response.getContactId());
//			Optional<String> costCenterInfo = userRepository.getCostCenter(response.getCostCenter());
//			Optional<String> roleInfo = userRepository.getRole(response.getRoleId());
//			
//				response.setDepartmentName(deptInfo.orElse(" "));
//
//				response.setOrganizationName(orgInfo.orElse(" "));
//
//				response.setBusinessUnitName(buInfo.orElse(" "));
//
//				response.setLocationName(locInfo.orElse(" "));
//
//				response.setContactName(contactInfo.orElse(" "));
//
//				response.setCostCenterName(costCenterInfo.orElse(" "));
//
//				response.setRoleName(roleInfo.orElse(" "));
//			
//			return response;
//		});
		return new APIResponsePaging(HttpStatus.OK.value(), HttpStatus.OK.name(), userPages.getContent(),
				new ArrayList<>(), userPages.getNumber(), userPages.getTotalElements(), userPages.getTotalPages());
	}

	public ResponseEntity<?> getUserByName(String name) {

		List<User> searchedUsers = userRepository.findByUserNameContainingIgnoreCase(name);
		return new ResponseEntity<>(searchedUsers, HttpStatus.OK);
	}

	public ResponseEntity<?> searchUser(UserSearchRequestDto model) {
		if (model.equals(new UserSearchRequestDto()) == true) {
			return new ResponseEntity<>("{\"message\":\"provide-atleast-one-parameter-for-search-criteria\"}",
					HttpStatus.BAD_REQUEST);
		}
		List<UserSearchResponse> searchResponse = userPageRepository.findBySearchParameters(model);
		return new ResponseEntity<>(searchResponse, HttpStatus.OK);
	}

	public ResponseEntity<?> forgotPassword(String email) throws Exception {

		Optional<User> userOptional = userRepository.findByEmailAndIamIdIsNotNull(email);
		if (userOptional.isPresent()) {
			EmailModel emailModel = new EmailModel();
			emailModel.setNameTo(userOptional.get().getFirstName() + "" + userOptional.get().getLastName());
			emailModel.setSendTo(email);
			emailModel.setSubject("Reset Password");
			emailModel.setNameTemplateHtml("resetPassword.html");
			Map<String, Object> objectModel = new HashMap<>();
			objectModel.put("resetPasswordUrl", resetPassword + userOptional.get().getIamId());
			emailModel.setObjectModel(objectModel);

			emailService.sendEmail(emailModel);

			return new ResponseEntity<>(
					"{\"message\":\"Check Your Email and Click on the link to reset your password.\"}", HttpStatus.OK);
		} else {

			return new ResponseEntity<>("{\"message\":\"this email is not Registered with us, Please try again!!\"}",
					HttpStatus.NOT_FOUND);
		}
	}

}
