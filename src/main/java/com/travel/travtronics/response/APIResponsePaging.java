package com.travel.travtronics.response;

import java.util.List;

public class APIResponsePaging {

	private String message;
	private Integer status;
	private List<?> data;
	private List<?> errors;
	private Integer currentPage;
	private Long totalElements;
	private Integer totalPages;

	public APIResponsePaging(Integer status, String message, List<?> data, List<?> errors, Integer currentPage,
			Long totalElements, Integer totalPages) {
		this.message = message;
		this.status = status;
		this.data = data;
		this.errors = errors;
		this.currentPage = currentPage;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

	public APIResponsePaging(Integer status, String message, List<?> data, List<?> errors) {
		this.message = message;
		this.status = status;
		this.data = data;
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public List<?> getErrors() {
		return errors;
	}

	public void setErrors(List<?> errors) {
		this.errors = errors;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}
