package com.travel.travtronics.api;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.travel.travtronics.config.RequestResponseLoggingInterceptor;
import com.travel.travtronics.user.entity.TokenModel;

@Service
@Configuration
@ConfigurationProperties(prefix = "com.travel.keycloak")
public class PropertiesModel {

	protected String urlKeycloakHost;

	protected String client_secret;

	protected String user;

	protected String credentials;

	protected String clientId;

	@Value("${keycloak.realm}")
	private String realm;

	protected RestTemplate restTemplate;

	@Autowired
	public PropertiesModel(RestTemplateBuilder builder) {

		restTemplate = builder.build();
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();

		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(clientHttpRequestFactory);

		clientHttpRequestFactory.setOutputStreaming(false);

		restTemplate.setRequestFactory(factory);

		restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));

	}

	public ResponseEntity<TokenModel> obtainToken() {
		String url = urlKeycloakHost + "auth/realms/" + realm + "/protocol/openid-connect/token";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", clientId);
		map.add("username", user);
		map.add("password", credentials);
		map.add("grant_type", "password");
		map.add("client_secret", client_secret);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		return restTemplate.postForEntity(url, request, TokenModel.class);
	}

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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

}
