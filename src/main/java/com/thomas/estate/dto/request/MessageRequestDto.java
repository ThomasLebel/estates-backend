package com.thomas.estate.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageRequestDto {
	@NotEmpty(message = "Message is required")
	@Schema(description = "Message à envoyer", example = "Nouveau message")
	private String message;

	@Schema(description = "ID de l'utilisateur qui envoie le message", example = "15")
	@NotNull(message = "UserID is required")
	private Integer user_id;

	@Schema(description = "ID de la location concernée", example = "8")
	@NotNull(message = "RentalID is required")
	private Integer rental_id;

}
