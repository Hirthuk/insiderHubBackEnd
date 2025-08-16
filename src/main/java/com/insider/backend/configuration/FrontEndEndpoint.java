package com.insider.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FrontEndEndpoint {
	
	@Value("${frontend_url}")
	public String frontendUrl;
	
	public String getFrontendUrl() {
		return this.frontendUrl;
	}

}
