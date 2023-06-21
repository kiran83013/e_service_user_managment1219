package com.travel.travtronics.service;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.travel.travtronics.dto.EmailModel;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

@Service
public class EmailService {
	
	@Value("${com.travel.email.from}")
	protected String fromMail;

	@Value("${com.travel.email.personalName}")
	protected String personalName;
	
	@Autowired
	it.ozimov.springboot.mail.service.EmailService emailService;

//	@Autowired
//	JavaMailSender emailSender;
	
	public ResponseEntity sendEmail(EmailModel template)
			throws UnsupportedEncodingException, CannotSendEmailException {
	
		final Email email = DefaultEmail.builder().from(new InternetAddress(fromMail, personalName))
						.to(Lists.newArrayList(new InternetAddress(template.getSendTo(), template.getNameTo())))
						.subject(template.getSubject()).body("").encoding("UTF-8").build();

		emailService.send(email, template.getNameTemplateHtml(), template.getObjectModel());

		return new ResponseEntity(HttpStatus.OK);
	}

}
