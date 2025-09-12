package com.thomas.estate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
public class MessageResponseDto {
	@Schema(description = "Message de succès", example = "Message de succès")
	private String message;

	public MessageResponseDto(String message) {
		this.message = message;
	}
}
