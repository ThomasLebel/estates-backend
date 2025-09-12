package com.thomas.estate.dto.request;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalRequestDto {
	@NotEmpty(message = "Name is required")
	@Schema(description = "Nom location", example = "Superbe Villa")
	private String name;

	@NotNull(message = "Surface is required")
	@Schema(description = "Surface location", example = "300")
	private BigDecimal surface;

	@NotNull(message = "Price is required")
	@Schema(description = "Prix location", example = "100")
	private BigDecimal price;

	@Schema(description = "Fichier image", example = "fichier multipart")
	private MultipartFile picture;

	@NotEmpty(message = "Description is required")
	@Schema(description = "Description location", example = "Superbe Villa en bord de mer")
	private String description;
}
