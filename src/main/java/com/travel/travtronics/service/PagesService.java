package com.travel.travtronics.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.travel.travtronics.dto.PageView;
import com.travel.travtronics.response.APIResponse;
import com.travel.travtronics.users.model.PagesModel;
import com.travel.travtronics.users.repository.PagesRepository;

@Service
public class PagesService {

	@Autowired
	PagesRepository pageRepository;

	public APIResponse create(PagesModel model) {
		try {
			List<PagesModel> list = new ArrayList<>();
			PagesModel save = pageRepository.save(model);
			list.add(save);
			return new APIResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.name(), list);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(),
					new ArrayList<>());
		}
	}

	public APIResponse update(PagesModel model) {
		List<PagesModel> list = new ArrayList<>();
		try {
			Optional<PagesModel> id = pageRepository.findById(model.getPageId());
			if (id.isPresent()) {
				model.setCreatedBy(id.get().getCreatedBy());
				model.setCreatedDate(id.get().getCreatedDate());
				PagesModel save = pageRepository.save(model);
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

	public APIResponse getById(Long pageId) {
		List<PagesModel> list = new ArrayList<>();
		Optional<PagesModel> id = pageRepository.findById(pageId);
		if (id.isPresent()) {
			list.add(id.get());
			return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
		} else {
			return new APIResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), new ArrayList<>());
		}
	}

	public APIResponse pagelist(Long applicationId) {
		List<Map<String, String>> list = pageRepository.findAllByList(applicationId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}

	public APIResponse list(Long organizationId) {
		List<Map<String, String>> list = pageRepository.findAllListByOrganizationId(organizationId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), list);
	}

	public APIResponse searchPage(String pageName) {
//		List<Map<String, String>> pages = pageRepository.findByGroupNameContainsIgnoreCase(pageName);
		List<PagesModel> pageData = pageRepository.findByPageNameIgnoreCaseEquals(pageName);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), pageData);
	}

	public APIResponse pagekey(String pageKey) {
		List<PagesModel> pagekey = pageRepository.findByPageKeyIgnoreCaseEquals(pageKey);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), pagekey);
	}

	public APIResponse getMenuPages(Integer roleId) {
		List<PageView> pageKeysInfo = pageRepository.findAllPageKeysByRoleId(roleId);
		return new APIResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), pageKeysInfo);
	}
}
