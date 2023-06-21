package com.travel.travtronics.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travtronics.dto.APIResponse;
import com.travel.travtronics.dto.LineMapDto;
import com.travel.travtronics.dto.MenuHeaderDto;
import com.travel.travtronics.exception.NotFoundException;
import com.travel.travtronics.request.Group;
import com.travel.travtronics.request.Line;
import com.travel.travtronics.request.LineCreationDto;
import com.travel.travtronics.request.LineUpdationDto;
import com.travel.travtronics.request.RootGroup;
import com.travel.travtronics.users.model.ApplicationsModel;
import com.travel.travtronics.users.model.PagesModel;
import com.travel.travtronics.users.model.UserHeaderMenu;
import com.travel.travtronics.users.model.UserLineMenu;
import com.travel.travtronics.users.repository.ApplicationsRepository;
import com.travel.travtronics.users.repository.HeaderMenuRepository;
import com.travel.travtronics.users.repository.LineMenuRepository;
import com.travel.travtronics.users.repository.PagesRepository;

@Service
public class MenuService {

	@Autowired
	LineMenuRepository lineMenuRepository;

	@Autowired
	HeaderMenuRepository headerMenuRepository;

	@Autowired
	PagesRepository userPagesRepository;

	@Autowired
	ApplicationsRepository applicationsRepository;

	ObjectMapper mapper = new ObjectMapper();

	@Transactional(rollbackFor = Exception.class)
	public APIResponse saveMenu(@RequestBody LineCreationDto model) {
		RootGroup json = null;

		try {

			json = mapper.readValue(model.getJson(), RootGroup.class);
			ArrayList<Line> lines = json.getGroup().getLines();

			for (int i = 0; i < lines.size(); i++) {
				Line line = lines.get(i);
				UserLineMenu menu = new UserLineMenu();
				menu.setHeaderId(model.getHeaderId());
				menu.setLevel(String.valueOf(i));
				if (Objects.nonNull(line.getLink())) {
					try {
						menu.setLink(getPage(line.getLink().longValue()));

					} catch (NotFoundException e) {
						// e.printStackTrace();
						return new APIResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
					}
				}
				menu.setName(line.getName());
				menu.setParentId(null);
				menu.setOrganizationId(line.getOrganizationId());
				menu.setCreatedBy(model.getCreatedBy());
				menu.setCreatedDate(new Date());
				menu.setMenuOrder(line.getMenuOrder() != null ? line.getMenuOrder() : 0);
				if (Objects.nonNull(line.getApplicationId())) {
					try {
						menu.setApplicationId(getPageApplication(line.getApplicationId().longValue()));

					} catch (NotFoundException e) {
						// e.printStackTrace();
						return new APIResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
					}
				}
				menu.setBadage(line.getBadage());
				menu.setBadageClass(line.getBadageClass());
				menu.setIcon(line.getIcon());
				menu.setNewTab(line.getNewTab());
				menu.setExternalLink(line.getExternalLink());
				menu.setIsCollapsable(line.getCollapse());

				// validateMenu(model.getHeaderId(), line.getName());
				UserLineMenu savedMenu = lineMenuRepository.save(menu);

				if (Objects.nonNull(line.getGroup().getLines()) && !line.getGroup().getLines().isEmpty()) {
					try {
						saveChildMenu(savedMenu, String.valueOf(i), line.getGroup().getLines(), model.getHeaderId(),
								model.getCreatedBy(), true);
					} catch (Exception e) {
						e.printStackTrace();
						return new APIResponse(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
					}
				}

			}
			return new APIResponse(HttpStatus.CREATED.name(), HttpStatus.CREATED.value());

		} catch (JsonParseException e) {

			e.printStackTrace();
			return new APIResponse(e.getOriginalMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		} catch (JsonMappingException e) {

			e.printStackTrace();
			return new APIResponse(e.getOriginalMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		} catch (IOException e) {

			e.printStackTrace();
			return new APIResponse(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

	}

	public Boolean validateMenu(Integer headerId, String name) throws Exception {
		Boolean validateMenu = lineMenuRepository.existsByHeaderIdAndName(headerId, name);

		if (validateMenu)
			throw new Exception(String.format("invalid line  menu information received with %s", name));
		else
			return false;

	}

	public PagesModel getPage(Long link) throws NotFoundException {

		return userPagesRepository.findByPageId(link)
				.orElseThrow(() -> new NotFoundException(link.toString(), PagesModel.class));

	}

	public ApplicationsModel getPageApplication(Long applicationId) throws NotFoundException {
		return applicationsRepository.findByApplicationId(applicationId)
				.orElseThrow(() -> new NotFoundException(applicationId.toString(), ApplicationsModel.class));

	}

	public void saveChildMenu(UserLineMenu savedMenu, String parentlevel, ArrayList<Line> childLines, Integer headerId,
			Integer userId, Boolean isSave) throws Exception {

		for (int i = 0; i < childLines.size(); i++) {
			Line line = childLines.get(i);

			String level = parentlevel + "-->".concat(String.valueOf(i));
			UserLineMenu menu = new UserLineMenu();
			menu.setHeaderId(headerId);
			menu.setLevel(level);
			menu.setName(line.getName());
			menu.setParentId(savedMenu);
			if (isSave) {
				menu.setCreatedBy(userId);
				menu.setCreatedDate(new Date());
			} else {
				menu.setCreatedBy(savedMenu.getCreatedBy());
				menu.setCreatedDate(savedMenu.getCreatedDate());
				menu.setUpdatedBy(userId);
				menu.setUpdatedDate(new Date());
			}
			menu.setBadage(line.getBadage());
			menu.setOrganizationId(line.getOrganizationId());
			menu.setBadageClass(line.getBadageClass());
			menu.setIcon(line.getIcon());
			menu.setNewTab(line.getNewTab());
			menu.setExternalLink(line.getExternalLink());
			menu.setIsCollapsable(line.getCollapse());
			menu.setMenuOrder(line.getMenuOrder() != null ? line.getMenuOrder() : 0);
			if (Objects.nonNull(line.getLink())) {

				menu.setLink(getPage(line.getLink().longValue()));

			}
			if (Objects.nonNull(line.getApplicationId())) {

				menu.setApplicationId(getPageApplication(line.getApplicationId().longValue()));

			}

			// validateMenu(headerId, line.getName());
			UserLineMenu savedChildMenu = lineMenuRepository.save(menu);

			if (Objects.nonNull(line.getGroup().getLines()) && !line.getGroup().getLines().isEmpty()) {
				saveChildMenu(savedChildMenu, level, line.getGroup().getLines(), headerId, userId, isSave);
			}

		}

	}

	public RootGroup getMenu(Integer headerId) {
		ArrayList<UserLineMenu> headerInfo = lineMenuRepository.findByHeaderId(headerId);

		if (headerInfo.isEmpty()) {

			return null;
		}

		Map<Boolean, ArrayList<UserLineMenu>> collectedjsonInfo = headerInfo.stream().collect(Collectors.partitioningBy(
				isParent -> Objects.isNull(isParent.getParentId()), Collectors.toCollection(ArrayList::new)));

		ArrayList<Line> lines = new ArrayList<>();

		Map<UserLineMenu, ArrayList<UserLineMenu>> childMap = collectedjsonInfo.get(false).stream()
				.collect(Collectors.groupingBy(UserLineMenu::getParentId,
						Collectors.collectingAndThen(Collectors.toList(),
								line -> line.stream().sorted(Comparator.comparingInt(UserLineMenu::getMenuOrder))
										.collect(Collectors.toCollection(ArrayList::new)))));

		collectedjsonInfo.get(true).stream().sorted(Comparator.comparingInt(UserLineMenu::getMenuOrder))
				.forEach(eachParent -> {
					Line line = new Line();
					line.setMenuOrder(eachParent.getMenuOrder());
					line.setName(eachParent.getName());
					if (Objects.nonNull(eachParent.getLink())) {
						line.setLink(eachParent.getLink().getPageId().intValue());
						line.setPath(eachParent.getLink().getPath());
					}
					if (Objects.nonNull(eachParent.getApplicationId())) {
						line.setApplicationId(eachParent.getApplicationId().getApplicationId().intValue());
						line.setUrl(eachParent.getApplicationId().getUrl());
					}
					line.setBadage(eachParent.getBadage());
					line.setBadageClass(eachParent.getBadageClass());
					line.setIcon(eachParent.getIcon());
					line.setNewTab(eachParent.getNewTab());
					line.setOrganizationId(eachParent.getOrganizationId());
					line.setExternalLink(eachParent.getExternalLink());
					line.setCollapse(eachParent.getIsCollapsable());

					if (childMap.containsKey(eachParent)) {
						ArrayList<UserLineMenu> childs = childMap.get(eachParent);
						line.setGroup(generateGroupTree(childs, childMap));
					}

					lines.add(line);
				});
		RootGroup root = new RootGroup();
		Group group = new Group();
		group.setLines(lines);
		root.setGroup(group);
		return root;

		// return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
		// List.of(root));
	}

	public Group generateGroupTree(ArrayList<UserLineMenu> childs,
			Map<UserLineMenu, ArrayList<UserLineMenu>> childMap) {
		Group group = new Group();
		ArrayList<Line> lines = new ArrayList<>();

		childs.stream().forEach(eachChild -> {
			Line line = new Line();
			line.setMenuOrder(eachChild.getMenuOrder());
			line.setName(eachChild.getName());
			if (Objects.nonNull(eachChild.getLink())) {
				line.setLink(eachChild.getLink().getPageId().intValue());
				line.setPath(eachChild.getLink().getPath());
			}
			if (Objects.nonNull(eachChild.getApplicationId())) {
				line.setApplicationId(eachChild.getApplicationId().getApplicationId().intValue());
				line.setUrl(eachChild.getApplicationId().getUrl());
			}
			// line.setLink(eachChild.getLink());
			// line.setApplicationId(eachChild.getApplicationId());
			line.setBadage(eachChild.getBadage());
			line.setOrganizationId(eachChild.getOrganizationId());
			line.setBadageClass(eachChild.getBadageClass());
			line.setIcon(eachChild.getIcon());
			line.setNewTab(eachChild.getNewTab());
			line.setExternalLink(eachChild.getExternalLink());
			line.setCollapse(eachChild.getIsCollapsable());

			if (childMap.containsKey(eachChild)) {
				ArrayList<UserLineMenu> childMenu = childMap.get(eachChild);
				line.setGroup(generateGroupTree(childMenu, childMap));
			}

			lines.add(line);
		});
		group.setLines(lines);
		return group;
	}

	public APIResponse saveMenuHeader(MenuHeaderDto model) throws NotFoundException {

		if (model.getId() != null && model.getId() != 0) {
			UserHeaderMenu updateMenu = updateMenuHeader(model);
			return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), List.of(updateMenu));
		}
		UserHeaderMenu menu = new UserHeaderMenu();
		menu.setOrganizationId(model.getOrganizationId());
		menu.setCreatedBy(model.getCreatedBy());
		menu.setDescription(model.getDescription());
		menu.setStartDate(model.getStartDate());
		menu.setEndDate(model.getEndDate());
		menu.setStatus(model.getStatus());
		menu.setName(model.getName());

		UserHeaderMenu savedHeader = headerMenuRepository.save(menu);

		return new APIResponse(HttpStatus.CREATED.name(), HttpStatus.CREATED.value(), List.of(savedHeader));
	}

	public UserHeaderMenu updateMenuHeader(MenuHeaderDto model) throws NotFoundException {

		UserHeaderMenu headerMenu = headerMenuRepository.findById(model.getId())
				.orElseThrow(() -> new NotFoundException(model.getId().toString(), UserHeaderMenu.class));

		headerMenu.setName(model.getName());
		headerMenu.setDescription(model.getDescription());
		headerMenu.setStartDate(model.getStartDate());
		headerMenu.setEndDate(model.getEndDate());
		headerMenu.setStatus(model.getStatus());
		headerMenu.setUpdatedBy(model.getUpdatedBy());
		headerMenu.setOrganizationId(model.getOrganizationId());
		return headerMenuRepository.save(headerMenu);

	}

	public APIResponse getMenuHeader(Integer id) throws NotFoundException {
		UserHeaderMenu headerMenu = headerMenuRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id.toString(), UserHeaderMenu.class));
		return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), List.of(headerMenu));
	}

	public APIResponse getMenuList(Long organizationId) {
		List<UserHeaderMenu> headerInfo = headerMenuRepository.findAllByOrganizationId(organizationId);
		return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), headerInfo);
	}

	@Transactional(rollbackFor = Exception.class)
	public APIResponse modifyMenu(@RequestBody LineUpdationDto model) {

		RootGroup json = null;
		LineMapDto mapLine = lineMenuRepository.getUserIdFromHeaderId(model.getHeaderId());

		if (Objects.isNull(mapLine)) {
			return new APIResponse(HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT.value());
		}

		Integer createdBy = mapLine.getCreatedBy();

		Date createdDate = mapLine.getCreatedDate();

		try {
			json = mapper.readValue(model.getJson(), RootGroup.class);
			lineMenuRepository.removeByHeaderId(model.getHeaderId());
			ArrayList<Line> lines = json.getGroup().getLines();
			for (int i = 0; i < lines.size(); i++) {
				Line line = lines.get(i);
				UserLineMenu menu = new UserLineMenu();
				menu.setHeaderId(model.getHeaderId());
				menu.setLevel(String.valueOf(i));
				menu.setName(line.getName());
				menu.setParentId(null);
				menu.setUpdatedBy(model.getUpdatedBy());
				menu.setCreatedBy(createdBy);
				menu.setCreatedDate(createdDate);
				menu.setUpdatedDate(new Date());
				menu.setBadage(line.getBadage());
				menu.setBadageClass(line.getBadageClass());
				menu.setIcon(line.getIcon());
				menu.setOrganizationId(line.getOrganizationId());
				menu.setNewTab(line.getNewTab());
				menu.setExternalLink(line.getExternalLink());
				menu.setIsCollapsable(line.getCollapse());
				menu.setMenuOrder(line.getMenuOrder() != null ? line.getMenuOrder() : 0);
				if (Objects.nonNull(line.getLink())) {
					try {
						menu.setLink(getPage(line.getLink().longValue()));

					} catch (NotFoundException e) {
						// e.printStackTrace();
						return new APIResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
					}
				}
				if (Objects.nonNull(line.getApplicationId())) {
					try {
						menu.setApplicationId(getPageApplication(line.getApplicationId().longValue()));

					} catch (NotFoundException e) {
						// e.printStackTrace();
						return new APIResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
					}
				}

				// validateMenu(model.getHeaderId(), line.getName());
				UserLineMenu savedMenu = lineMenuRepository.save(menu);

				if (Objects.nonNull(line.getGroup().getLines()) && !line.getGroup().getLines().isEmpty()) {
					try {
						saveChildMenu(savedMenu, String.valueOf(i), line.getGroup().getLines(), model.getHeaderId(),
								model.getUpdatedBy(), false);
					} catch (Exception e) {
						e.printStackTrace();
						return new APIResponse(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST.value());
					}
				}

			}
			return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value());

		} catch (JsonParseException e) {

			e.printStackTrace();
			return new APIResponse(e.getOriginalMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		} catch (JsonMappingException e) {

			e.printStackTrace();
			return new APIResponse(e.getOriginalMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		} catch (IOException e) {

			e.printStackTrace();
			return new APIResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		}

	}

	public APIResponse getMenuLines(Integer headerId) {
		RootGroup menu = getMenu(headerId);
		if (Objects.isNull(menu))
			return new APIResponse(HttpStatus.NO_CONTENT.name(), HttpStatus.NO_CONTENT.value());
		else
			return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), List.of(menu));
	}

}
