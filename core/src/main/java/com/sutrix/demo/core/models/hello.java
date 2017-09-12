package com.sutrix.demo.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;


@Model(adaptables = SlingHttpServletRequest.class)
public class hello {

	@Self
	private SlingHttpServletRequest request;

	private String fileReference;

	@PostConstruct
	public void activate() {
		ValueMap properties = request.getResource().getValueMap();
		fileReference = properties.get("fileReference", "");
	}

	public String getFileReference() {
		return fileReference;
	}
	
}
