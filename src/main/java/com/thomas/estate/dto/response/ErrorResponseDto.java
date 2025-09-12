package com.thomas.estate.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "Réponse en cas d'erreur")
public class ErrorResponseDto {
	@Schema(description = "Message d'erreur", example = "Message de l'erreur recontrée")
	private String error;
	@Schema(description = "Code HTTP", example = "400")
	private int status;

	public ErrorResponseDto(String error, int status) {

		this.error = error;
		this.status = status;
	}
}
