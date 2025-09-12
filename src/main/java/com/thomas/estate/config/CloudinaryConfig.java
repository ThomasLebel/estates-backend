package com.thomas.estate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {

	@Value("${CLOUDINARY_CLOUD_NAME}")
	String cloudName;

	@Value("${CLOUDINARY_API_KEY}")
	String apiKey;

	@Value("${CLOUDINARY_API_SECRET}")
	String apiSecret;

	@Bean
	Cloudinary cloudinary() {
		return new Cloudinary(ObjectUtils.asMap("cloud_name", cloudName, "api_key", apiKey, "api_secret", apiSecret));
	}
}
