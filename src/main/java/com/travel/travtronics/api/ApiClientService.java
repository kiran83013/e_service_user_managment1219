package com.travel.travtronics.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import com.travel.travtronics.config.RequestResponseLoggingInterceptor;


@Service
@ConfigurationProperties(prefix="com.travel.travtronics.url")
public class ApiClientService extends ApiClientParent{
	
	@Autowired
	public ApiClientService(RestTemplateBuilder builder) {
		restTemplate = builder.build();

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(
				requestFactory);
		requestFactory.setOutputStreaming(false);
		restTemplate.setRequestFactory(bufferingClientHttpRequestFactory);

		restTemplate.setInterceptors(Collections.singletonList(new RequestResponseLoggingInterceptor()));
	}
	
//	public ResponseEntity<String> obtainToken_Google(String authorizationCode) throws URISyntaxException {
//
//		String url = google_auth_host + "token";
//		String redirectURI = redirect_uri_dashboard;
//		
//		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
//				.queryParam("grant_type", "authorization_code").queryParam("redirect_uri", redirectURI)
//				.queryParam("code", authorizationCode).queryParam("client_id", google_client_id)
//				.queryParam("client_secret", google_secret_id);
//
//		try {
//			return restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST, null, String.class);
//		} catch (HttpClientErrorException ex) {
//			ex.printStackTrace();
//			return new ResponseEntity<String>(ex.getMessage(), ex.getStatusCode());
//		}
//
//	}
//	
//	public ResponseEntity<String> obtainProfile_Google(String access_token) throws URISyntaxException {
//
//		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//		headers.add("Authorization", "Bearer " + access_token);
////		headers.add("X-RestLi-Protocol-Version", "2.0.0");
////		headers.add("Connection", "Keep-Alive");
//
//		try {
//			RequestEntity<Object> entity = new RequestEntity<>(headers, HttpMethod.GET, new URI(google_userprofile));
//			return restTemplate.exchange(entity, String.class);
//		} catch (HttpClientErrorException ex) {
//			return new ResponseEntity<String>(ex.getMessage(), ex.getStatusCode());
//		}
//
//	}

}
