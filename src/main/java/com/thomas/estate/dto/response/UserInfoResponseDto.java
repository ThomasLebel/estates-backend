package com.thomas.estate.dto.response;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponseDto {
	@Schema(description = "ID utilisateur", example = "18")
	private Integer id;
	@Schema(description = "Nom utilisateur", example = "Pseudo18")
	private String name;
	@Schema(description = "Email utilisateur", example = "pseudo18@gmail.com")
	private String email;
	@Schema(description = "Date création de l'utilisateur", example = "2025-08-31")
	private LocalDate created_at;
	@Schema(description = "Date mise à jour de l'utilisateur", example = "2025-08-31")
	private LocalDate updated_at;

	public UserInfoResponseDto(Integer id, String name, String email, LocalDate created_at, LocalDate updated_at) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}
