package com.travel.travtronics.dto;

import java.util.Map;

public class EmailModel {

	private String sendTo;
	private String nameTo;
	private String subject;
	private Map<String, Object> objectModel;
	private String nameTemplateHtml;
	
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}
	public String getNameTo() {
		return nameTo;
	}
	public void setNameTo(String nameTo) {
		this.nameTo = nameTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Map<String, Object> getObjectModel() {
		return objectModel;
	}
	public void setObjectModel(Map<String, Object> objectModel) {
		this.objectModel = objectModel;
	}
	public String getNameTemplateHtml() {
		return nameTemplateHtml;
	}
	public void setNameTemplateHtml(String nameTemplateHtml) {
		this.nameTemplateHtml = nameTemplateHtml;
	}
}
