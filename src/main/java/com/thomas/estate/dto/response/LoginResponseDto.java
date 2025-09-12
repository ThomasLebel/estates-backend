package com.thomas.estate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

	@Schema(description = "Token", example = "SRBX2TqT8Vp0kuHHBtBghmbhvrVsi4rIVH36g7dlrzadvQQXX5155rWDq1ky1RRF")
	private String token;

	public LoginResponseDto(String token) {
		this.token = token;
	}

}
