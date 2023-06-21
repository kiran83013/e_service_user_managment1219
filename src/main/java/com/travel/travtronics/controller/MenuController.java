package com.travel.travtronics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travtronics.dto.APIResponse;
import com.travel.travtronics.dto.MenuHeaderDto;
import com.travel.travtronics.exception.NotFoundException;
import com.travel.travtronics.request.LineCreationDto;
import com.travel.travtronics.request.LineUpdationDto;
import com.travel.travtronics.service.MenuService;

@RestController
public class MenuController {

	@Autowired
	MenuService menuService;

	@PostMapping(value = "menu-line")
	public APIResponse createMenu(@RequestBody LineCreationDto model) {

		return menuService.saveMenu(model);
	}

	@GetMapping(value = "get-menu-line")
	public APIResponse getMenu(@RequestParam Integer headerId) {

		return menuService.getMenuLines(headerId);
	}

	@PostMapping(value = "menu-header")
	public APIResponse createMenuHeader(@RequestBody MenuHeaderDto model) throws NotFoundException {

		return menuService.saveMenuHeader(model);
	}

	@GetMapping(value = "get-menu-header")
	public APIResponse getMenuHeader(@RequestParam Integer id) throws NotFoundException {

		return menuService.getMenuHeader(id);
	}

	@GetMapping(value = "get-menu-header-list")
	public APIResponse getMenuList(@RequestParam Long organizationId) {

		return menuService.getMenuList(organizationId);
	}

	@PutMapping(value = "modify/menu-line")
	public APIResponse modifyMenu(@RequestBody LineUpdationDto model) {

		return menuService.modifyMenu(model);
	}

}
