package com.travel.travtronics.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.travel.travtronics.api.ApiClientService;
import com.travel.travtronics.api.PropertiesModel;
import com.travel.travtronics.config.RequestResponseLoggingInterceptor;
import com.travel.travtronics.dto.RoleDto;
import com.travel.travtronics.exception.NotFoundException;
import com.travel.travtronics.request.AceessTokenRequestModel;
import com.travel.travtronics.request.LoginRequestModel;
import com.travel.travtronics.response.AccessTokenResponseModel;
import com.travel.travtronics.response.LoginResponseModel;
import com.travel.travtronics.response.RefreshTokenResponseModel;
import com.travel.travtronics.user.entity.AttrModel;
import com.travel.travtronics.user.entity.Credentials;
import com.travel.travtronics.user.entity.GetToken;
import com.travel.travtronics.user.entity.IdentityProviderModel;
import com.travel.travtronics.user.entity.KeycloakUserModel;
import com.travel.travtronics.user.entity.SignUpIAMModel;
import com.travel.travtronics.user.entity.TokenModel;
//import com.travel.travtronics.srm.entity.KeycloakUserModel;
//import com.travel.travtronics.srm.model.AccessTokenResponseModel;
//import com.travel.travtronics.srm.model.AceessTokenRequestModel;
//import com.travel.travtronics.srm.model.AttrModel;
//import com.travel.travtronics.srm.model.Credentials;
//import com.travel.travtronics.srm.model.GetToken;
//import com.travel.travtronics.srm.model.IdentityProviderModel;
//import com.travel.travtronics.srm.model.LoginRequestModel;
//import com.travel.travtronics.srm.model.LoginResponseModel;
//import com.travel.travtronics.srm.model.RefreshTokenResponseModel;
//import com.travel.travtronics.srm.model.SignUpIAMModel;
//import com.travel.travtronics.srm.model.TokenModel;
//import com.travel.travtronics.srm.repository.UserExtraIntoDto;
import com.travel.travtronics.users.model.User;
import com.travel.travtronics.users.repository.UserExtraIntoDto;
import com.travel.travtronics.users.repository.UserRepository;
import com.travel.travtronics.users.repository.UsersPermissionModelRepository;
import com.travel.travtronics.users.repository.UsersRoleRepository;

@Service
@ConfigurationProperties(prefix = "com.travel.keycloak")
public class AuthenticationService {

	@Autowired
	PropertiesModel kcModel;

	@Autowired
	ApiClientService apiClientService;

	private String urlKeycloakHost;

	private RestTemplate restTemplate;

	protected String client_secret;
	protected String user;
	protected String credentials;
	protected String client_id;

	@Value("${keycloak.realm}")
	private String realm;

	@Value("${com.travel.url.defaultPassword}")
	private String defaultPassword;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UsersRoleRepository roleRepository;

	@Autowired
	UsersPermissionModelRepository permissionRepository;

	@Autowired
	MenuService menuService;

	private String userRoleId;
	private String adminRoleId;

	@Autowired
	public AuthenticationService(RestTemplateBuilder builder) {
		restTemplate = builder.build();

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(
				requestFactory);
		requestFactory.setOutputStreaming(false);
		restTemplate.setRequestFactory(bufferingClientHttpRequestFactory);

		restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
	}

	public ResponseEntity<String> createUser(KeycloakUserModel userModel) {

		SignUpIAMModel model = constructSignUpIAM(userModel);

		String role = null;
//		if (userModel.getRole() != null && !userModel.getRole().isEmpty())
//			role = userModel.getRole();
//		else
//			role = new String("user");

		ResponseEntity<String> constructUserResponse = createUserKeyCloak(model, role);
		String iamId = getUserIdFromKeyCloak(constructUserResponse);
		if (userModel.getPassword() != null && !userModel.getPassword().isEmpty())
			updatePassword(iamId, userModel.getPassword());
		else
			updatePassword(iamId, defaultPassword);
		return new ResponseEntity<String>(iamId, HttpStatus.OK);

	}

	public ResponseEntity<String> createUserAndRole(KeycloakUserModel userModel) {

		SignUpIAMModel model = constructSignUpIAM(userModel);

		String role = null;
		if (userModel.getRole() != null && !userModel.getRole().isEmpty())
			role = userModel.getRole();

		ResponseEntity<String> constructUserResponse = createUserKeyCloak(model, role);
		String iamId = getUserIdFromKeyCloak(constructUserResponse);
		if (userModel.getPassword() != null && !userModel.getPassword().isEmpty())
			updatePassword(iamId, userModel.getPassword());
		else
			updatePassword(iamId, defaultPassword);
		return new ResponseEntity<String>("{\"iamId\":\"" + iamId + "\"}", HttpStatus.OK);

	}

	public ResponseEntity<TokenModel> obtainToken(GetToken model) {
		String url = urlKeycloakHost + "auth/realms/" + realm + "/protocol/openid-connect/token";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", model.getRe1());
		map.add("username", model.getRe5());
		map.add("password", model.getRe2());
		map.add("grant_type", model.getRe3());
		map.add("client_secret", model.getRe4());
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		return new ResponseEntity<>(restTemplate.postForEntity(url, request, TokenModel.class).getBody(),
				HttpStatus.OK);
	}

	public ResponseEntity<?> login(LoginRequestModel model)
			throws NotFoundException, JsonParseException, JsonMappingException, IOException {

		User user = userRepository.findByEmail(model.getEmail())
				.orElseThrow(() -> new NotFoundException(model.getEmail(), User.class));

		String url = urlKeycloakHost + "auth/realms/" + realm + "/protocol/openid-connect/token";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", client_id);
		map.add("username", model.getEmail());
		map.add("password", model.getPassword());
		map.add("grant_type", credentials);
		map.add("client_secret", client_secret);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		String loginResponse = restTemplate.postForEntity(url, request, String.class).getBody();

		// Optional<UserRoleModel> role = roleRepository.findByRoleId(user.getRoleId());

		Optional<RoleDto> role = roleRepository.findByRoleInfoId(user.getRoleId().intValue());

//		if (Objects.nonNull(user.getWtId())
//				&& (Objects.nonNull(user.getWtProfile()) || user.getWtProfile().length() != 0)
//				&& Objects.nonNull(user.getWtFlag()) && user.getWtFlag() != true) {
//			System.out.println("---cheking------");
//			Map<String, Object> attr = new HashMap<>();
//
//			attr.put("wtId", user.getWtId());
//			attr.put("wtProfile", user.getWtProfile());
//			attr.put("userId", user.getUserId());
//			attr.put("appRoleId", roleRepository.getRoleKeyByRoleId(user.getRoleId()));
//			addAttributes(user.getIamId(), attr);
//
//			user.setWtFlag(true);
//			userRepository.save(user);
//		}

		LoginResponseModel response = new LoginResponseModel();
		response.setLoginResponse(loginResponse);
		if (role.isPresent()) {
			response.setRoleId(role.get().getId().longValue());
			response.setRoleName(role.get().getRoleName());
			response.setRoleKey(role.get().getRoleKey());
			response.setMenu(menuService.getMenu(role.get().getMenuId().intValue()));
			response.setMenuId(role.get().getMenuId().intValue());
			response.setLandingPageId(role.get().getPageId().intValue());
			response.setApplicationId(role.get().getApplicationId());
			response.setPageKey(role.get().getPageKey());
			response.setUrl(role.get().getUrl());
			response.setPagePath(role.get().getPagePath());
			response.setApplicationName(role.get().getApplicationName());
		}
		response.setUserId(user.getUserId());
		response.setUserName(user.getUserName());
		response.setFullName(user.getFirstName() + " " + user.getLastName());

		UserExtraIntoDto userInfoDeatils = userRepository.getUserInfoDeatils(user.getUserId());

		if (Objects.nonNull(userInfoDeatils)) {
			response.setBusinessUnit(userInfoDeatils.getBusinessUnit());
			response.setBusinessUnitId(userInfoDeatils.getBusinessUnitId());
			response.setOrg(userInfoDeatils.getOrg());
			response.setOrgId(userInfoDeatils.getOrganizationId());
			response.setDept(userInfoDeatils.getDept());
			response.setDeptId(userInfoDeatils.getDeptId());
			response.setCostCenter(userInfoDeatils.getCostCenter());
			response.setCostCenterId(userInfoDeatils.getCostCenterId());
			response.setLocationId(userInfoDeatils.getLocationId());
			response.setLocationInfo(userInfoDeatils.getLocationInfo());
			response.setBizId(user.getBizId());
			response.setContactId(user.getContactId());
			response.setBizName(userInfoDeatils.getBizName());
			response.setContactName(userInfoDeatils.getContactName());
		}
		return new ResponseEntity<LoginResponseModel>(response, HttpStatus.OK);
	}

	private String getUserIdFromKeyCloak(ResponseEntity<String> response) {

		String location = response.getHeaders().get("Location").get(0).toString();
		String[] locationSplit = location.split("/");
		String keycloakId = locationSplit[8];
		return keycloakId;
	}

	public ResponseEntity<String> updatePassword(String iamID, String password) {
		String url = urlKeycloakHost + "auth/admin/realms/" + realm + "/users/" + iamID + "/reset-password";
		Credentials credentials = new Credentials("password", password, false);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(apiClientService.obtainToken().getBody().getAccess_token());
		HttpEntity<Credentials> request = new HttpEntity<Credentials>(credentials, headers);
		restTemplate.put(url, request, String.class);
		return new ResponseEntity<String>("{\"message\":\"Password updated successfully\"}", HttpStatus.OK);
	}

	public SignUpIAMModel constructSignUpIAM(KeycloakUserModel userModel) {
		SignUpIAMModel signUp = new SignUpIAMModel();
		signUp.setEmail(userModel.getEmail().toLowerCase());
		signUp.setUsername(userModel.getUserName());
		signUp.setFirstName(userModel.getFirstName());
		signUp.setLastName(userModel.getLastName());
		signUp.setEnabled(true);

		// Map<String, Object> attributes = new HashMap<>();
		// attributes.put("userId", userModel.getUserId());
		// attributes.put("appRoleId", userModel.getAppRoled());
		// signUp.setAttributes(attributes);
		return signUp;
	}

	public ResponseEntity<String> createUserKeyCloak(SignUpIAMModel model, String role) {
		String url = urlKeycloakHost + "auth/admin/realms/" + realm + "/users";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// obtaining the admin token to create an user
		headers.setBearerAuth(apiClientService.obtainToken().getBody().getAccess_token());

		// Sending the signup json with the admin token to create an user
		HttpEntity<SignUpIAMModel> request = new HttpEntity<SignUpIAMModel>(model, headers);

		/**
		 * Storing user on the keycloak
		 */
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		if (role != null && !role.isEmpty()) {
			// JSONObject iamIDJsonobject;
			// iamIDJsonobject = new JSONObject(response.getBody());
			String iamId = getUserIdFromKeyCloak(response);
			// String iamId = iamIDJsonobject.getString("iamId");
			assignUserRoleOnKeyCloak(role, iamId);
		}

		return response;
	}

	public ResponseEntity<String> assignUserRoleOnKeyCloak(String role, String iamId) {
		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(apiClientService.obtainToken().getBody().getAccess_token());

		HttpEntity<String> request = new HttpEntity<>(headers);

		String url = urlKeycloakHost + "auth/admin/realms/" + realm + "/users/" + iamId + "/groups/";

		if (role.equals("user")) {
			url += userRoleId;
		} else if (role.equals("admin")) {
			url += adminRoleId;
		}

		try {
			restTemplate.put(url, request);
			return new ResponseEntity<String>(
					"{\"message\":\"User role added succesfully, Please confirm your email address by checking your inbox\"}",
					HttpStatus.ACCEPTED);

		} catch (HttpClientErrorException ex) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<String> getUserRolesInKeycloak(String iamId) {
		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(apiClientService.obtainToken().getBody().getAccess_token());

		HttpEntity<String> request = new HttpEntity<>(headers);

		String url = urlKeycloakHost + "auth/admin/realms/" + realm + "/users/" + iamId + "/groups";

		try {
			RequestEntity<Object> entity = null;
			try {
				entity = new RequestEntity<>(headers, HttpMethod.GET, new URI(url));
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return restTemplate.exchange(entity, String.class);
//			return restTemplate.getForEntity(url, String.class);

		} catch (HttpClientErrorException ex) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * https://www.keycloak.org/docs/4.8/server_admin/#_identity_broker_first_login
	 * 
	 * @param iamid
	 * @param model
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public ResponseEntity<String> assignIdentityProviderToKeyCloak(String iamid, IdentityProviderModel model)
			throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		try {

			String url = urlKeycloakHost + "auth/admin/realms/" + realm + "/users/" + iamid + "/federated-identity/"
					+ model.getIdentityProvider();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setBearerAuth(apiClientService.obtainToken().getBody().getAccess_token());
			HttpEntity<IdentityProviderModel> request = new HttpEntity<IdentityProviderModel>(model, headers);

			restTemplate.postForEntity(url, request, String.class);
			return new ResponseEntity<String>("{\"message\":\"User Social account succesfully created\"}",
					HttpStatus.ACCEPTED);

		} catch (HttpClientErrorException ex) {
			return ErrorMessageService.clientErrorException(ex);
		}

	}

	public ResponseEntity<?> refreshToken(AceessTokenRequestModel model) {
		String url = urlKeycloakHost + "auth/realms/" + realm + "/protocol/openid-connect/token";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "refresh_token");
		map.add("client_id", client_id);
		map.add("client_secret", client_secret);
		map.add("refresh_token", model.getToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		return new ResponseEntity<>(restTemplate.postForEntity(url, request, RefreshTokenResponseModel.class).getBody(),
				HttpStatus.OK);
	}

	public String getUrlKeycloakHost() {
		return urlKeycloakHost;
	}

	public void setUrlKeycloakHost(String urlKeycloakHost) {
		this.urlKeycloakHost = urlKeycloakHost;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getDefaultPassword() {
		return defaultPassword;
	}

	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getAdminRoleId() {
		return adminRoleId;
	}

	public void setAdminRoleId(String adminRoleId) {
		this.adminRoleId = adminRoleId;
	}

	public ResponseEntity<?> verifyAccessToken(AceessTokenRequestModel model) {

		String url = urlKeycloakHost + "auth/realms/" + realm + "/protocol/openid-connect/token/introspect";

		// String url =
		// "http://192.178.10.132:8080/auth/realms/Travtronics/protocol/openid-connect/token/introspect";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("token", model.getToken());
		map.add("client_id", client_id);
		map.add("client_secret", client_secret);

		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
		AccessTokenResponseModel body = restTemplate.postForEntity(url, request, AccessTokenResponseModel.class)
				.getBody();

		if (body.getActive() == true) {
			body.setTimestamp(LocalDateTime.now().toString());
			body.setMessage("valid-token");
			body.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<>(body, HttpStatus.OK);
		} else {
			body.setTimestamp(LocalDateTime.now().toString());
			body.setMessage("invalid-or-expired-token-received");
			body.setStatusCode(HttpStatus.UNAUTHORIZED.value());
			return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
		}

	}

	public ResponseEntity<?> addAttributes(String iamId, Map<String, Object> map) {
		// String url =
		// "http://192.178.10.132:8080/auth/admin/realms/Travtronics/users/" + iamId;

		String url = urlKeycloakHost + "auth/admin/realms/" + realm + "/users/" + iamId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(apiClientService.obtainToken().getBody().getAccess_token());
//		Map<String, Object> map = new HashMap<>();
//
//		map.put("wtId", 123);
//		map.put("wtProfile", "Profile");
//		map.put("userId", 123);

		AttrModel attr = new AttrModel();
		attr.setAttributes(map);

		HttpEntity<?> request = new HttpEntity<>(attr, headers);

		restTemplate.put(url, request, String.class);
		return ResponseEntity.ok("{\"message\":\"OK\"}");

	}

	public ResponseEntity<?> updateUserClaim(Long userId, String role) throws NotFoundException {

		User user = userRepository.findByUserId(userId)
				.orElseThrow(() -> new NotFoundException(userId.toString(), User.class));

		Map<String, Object> map = new HashMap<>();

		map.put("wtId", Objects.nonNull(user.getWtId()) ? user.getWtId() : 0);
		map.put("wtProfile", StringUtils.isNotBlank(user.getWtProfile()) ? user.getWtProfile() : "");
		map.put("userId", user.getUserId());
		map.put("appRoleId", StringUtils.deleteWhitespace(role));

		try {

			return addAttributes(user.getIamId(), map);

		} catch (Exception ex) {
			ex.printStackTrace();

			return new ResponseEntity<>("{\"message\":\"unexpected error occurred\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	public ResponseEntity<?> userLogout(Long userId) {
		Optional<User> user = userRepository.findByUserIdAndIamIdIsNotNull(userId);
		if (Objects.isNull(user))
			return new ResponseEntity<>("{\"message\":\"some-thing-went-wrong\"}", HttpStatus.NOT_FOUND);
		else {
			String url = kcModel.getUrlKeycloakHost() + "auth/admin/realms/" + kcModel.getRealm() + "/users/"
					+ user.get().getIamId() + "/logout";
			HttpHeaders headers = new HttpHeaders();
			headers.setBearerAuth(kcModel.obtainToken().getBody().getAccess_token());

			HttpEntity<?> request = new HttpEntity<>(headers);
			String logout = null;
			ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, request, String.class);
			if (postForEntity.getStatusCodeValue() == 204)
				logout = "{\"message\":\"logged-out-successfully....\"}";
			else
				logout = "{\"message\":\"unable-to-logout...\"}";
			return new ResponseEntity<>(logout, HttpStatus.OK);
		}

	}
}
