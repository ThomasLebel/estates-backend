package com.thomas.estate.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
	@NotEmpty(message = "Name is required")
	@Schema(description = "Nom utilisateur", example = "John")
	private String name;

	@Email(message = "Email should be valid")
	@NotEmpty(message = "Email is required")
	@Schema(description = "Email utilisateur", example = "john@gmail.com")
	private String email;

	@NotEmpty(message = "Password is required")
	@Schema(description = "Mot de passe utilisateur", example = "MotDePasseSécurisé+")
	private String password;

}
