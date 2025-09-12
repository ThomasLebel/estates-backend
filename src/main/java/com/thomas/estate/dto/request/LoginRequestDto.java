package com.thomas.estate.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

	@Email(message = "Email should be valid")
	@NotEmpty(message = "Email is required")
	@Schema(description = "Email", example = "exemple@email.com")
	private String email;

	@NotEmpty(message = "Password is required")
	@Schema(description = "Mot de passe", example = "motDePasseSécurisé+")
	private String password;
}
