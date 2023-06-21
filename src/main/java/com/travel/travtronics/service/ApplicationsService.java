package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.users.model.ApplicationsModel;
import com.travel.travtronics.users.repository.ApplicationsRepository;

@Service
public class ApplicationsService {

	@Autowired
	ApplicationsRepository applicationRepository;

	public APIResponse create(ApplicationsModel application) {
		try {
			List<ApplicationsModel> list = new ArrayList<>();
			ApplicationsModel save = applicationRepository.save(application);
			list.add(save);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse update(ApplicationsModel application) {
		List<ApplicationsModel> list = new ArrayList<>();
		try {
			Optional<ApplicationsModel> id = applicationRepository.findByApplicationId(application.getApplicationId());
			if (id.isPresent()) {
				application.setCreatedBy(id.get().getCreatedBy());
				application.setCreatedDate(id.get().getCreatedDate());
				ApplicationsModel save = applicationRepository.save(application);
				list.add(save);
				return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
			} else {
				return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse getById(Long applicationId) {
		List<ApplicationsModel> list = new ArrayList<>();
		Optional<ApplicationsModel> id = applicationRepository.findByApplicationId(applicationId);
		if (id.isPresent()) {
			list.add(id.get());
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}

	public APIResponse list(Long organizationId) {
		List<ApplicationsModel> list = applicationRepository.findByOrganizationId(organizationId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}

	public APIResponse searchApplication(String applicationName) {
//		List<Map<String, String>> group = applicationRepository.findByGroupNameContainsIgnoreCase(applicationName);
		List<ApplicationsModel> appData = applicationRepository.findByApplicationNameIgnoreCaseEquals(applicationName);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), appData);
	}

}
