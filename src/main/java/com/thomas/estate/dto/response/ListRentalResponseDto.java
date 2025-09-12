package com.thomas.estate.dto.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Réponse contenant la liste des locations")
public class ListRentalResponseDto {
	@Schema(description = "Lise des locations")
	private List<RentalResponseDto> rentals;

	public ListRentalResponseDto(List<RentalResponseDto> rentals) {
		this.rentals = rentals;
	}
}
