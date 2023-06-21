package com.travel.travtronics.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.io.Files.getFileExtension;
import freemarker.template.Configuration;

import it.ozimov.springboot.mail.service.TemplateService;
import it.ozimov.springboot.mail.service.exception.TemplateException;

@Service
public class HtmlTemplateConfig implements TemplateService{

	@Autowired
	private Configuration freemarkerConfiguration;

	@Override
	@NonNull
	public String mergeTemplateIntoString(final @NonNull String templateReference,
			final @NonNull Map<String, Object> model) throws IOException, TemplateException {
		final String trimmedTemplateReference = templateReference.trim();
		checkArgument(!isNullOrEmpty(trimmedTemplateReference), "The given template is null, empty or blank");
		if (trimmedTemplateReference.contains("."))
			checkArgument(Objects.equals(getFileExtension(trimmedTemplateReference), expectedTemplateExtension()),
					"Expected a Freemarker template file with extension 'ftl', while '%s' was given",
					getFileExtension(trimmedTemplateReference));

		try {
			final String normalizedTemplateReference = trimmedTemplateReference.endsWith(expectedTemplateExtension())
					? trimmedTemplateReference
					: trimmedTemplateReference + '.' + expectedTemplateExtension();
			return FreeMarkerTemplateUtils.processTemplateIntoString(
					freemarkerConfiguration.getTemplate(normalizedTemplateReference, Charset.forName("UTF-8").name()),
					model);
		} catch (Exception e) {
			throw new TemplateException(e);
		}
	}

	@Override
	public String expectedTemplateExtension() {
		return "html";
	}
	
	

}
