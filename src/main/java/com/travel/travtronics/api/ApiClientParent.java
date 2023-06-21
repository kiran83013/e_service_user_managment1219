package com.travel.travtronics.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.travel.travtronics.user.entity.TokenModel;

//@ConfigurationProperties(prefix = "com.travel.keycloak")
public class ApiClientParent {

	protected RestTemplate restTemplate;
	@Value("${com.travel.keycloak.urlKeycloakHost}")
	protected String urlKeycloakHost;
	@Value("${com.travel.keycloak.client_secret}")
	protected String client_secret;
	@Value("${com.travel.keycloak.user}")
	protected String user;
	@Value("${com.travel.keycloak.credentials}")
	protected String credentials;
	@Value("${com.travel.keycloak.client_id}")
	protected String client_id;

//	protected String google_userprofile;
//	protected String google_auth_host;
//	protected String google_client_id;
//	protected String google_secret_id;
//	protected String redirect_uri_dashboard;

	@Value("${keycloak.realm}")
	private String realm;

	public ResponseEntity<TokenModel> obtainToken() {

		String url = urlKeycloakHost + "auth/realms/" + realm + "/protocol/openid-connect/token";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", client_id);
		map.add("username", user);
		map.add("password", credentials);
		map.add("grant_type", "password");
		map.add("client_secret", client_secret);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		return restTemplate.postForEntity(url, request, TokenModel.class);
	}

//	protected ResponseEntity<?> sendGETMethod(String url, Class<?> responseType, Map<String, String> uriVariables) {
//
//		uriVariables.put("Content-Type", "application/json");
//		uriVariables.put("accept", "*/*");
//
//		return restTemplate.getForEntity(url, responseType, uriVariables);
//	}
//
//	protected ResponseEntity<?> sendPOSTMethod(String url, Object request, Class<?> responseType,
//			MultiValueMap<String, String> uriVariables) {
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
////		headers.setBearerAuth(obtainToken().getAccess_token());
//
//		HttpEntity<Object> entity = new HttpEntity<>(request, headers);
//
//		// restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
//		return restTemplate.postForEntity(url, entity, responseType);
//
//		// return restTemplate.postForEntity(url, request, responseType, uriVariables);
//	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getUrlKeycloakHost() {
		return urlKeycloakHost;
	}

	public void setUrlKeycloakHost(String urlKeycloakHost) {
		this.urlKeycloakHost = urlKeycloakHost;
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

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

}
