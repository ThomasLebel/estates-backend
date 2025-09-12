package com.thomas.estate.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalResponseDto {

	@Schema(description = "ID location", example = "18")
	private Integer id;
	@Schema(description = "nom location", example = "Villa moderne")
	private String name;
	@Schema(description = "surface location", example = "300")
	private BigDecimal surface;
	@Schema(description = "prix location", example = "1000")
	private BigDecimal price;
	@Schema(description = "photo location", example = "https://res.cloudinary.com/dkf48p2ah/image/upload/v1756647734/pq9tcsku4bbstgsugact.png")
	private String picture;
	@Schema(description = "description location", example = "Superbe villa en bord de mer")
	private String description;
	@Schema(description = "id propriétaire location", example = "20")
	private Integer owner_id;
	@Schema(description = "date création location", example = "2025-08-31")
	private LocalDate created_at;
	@Schema(description = "date mise à jour location", example = "2025-09-01")
	private LocalDate updated_at;

	public RentalResponseDto(Integer id, String name, BigDecimal surface, BigDecimal price, String picture,
			String description, Integer owner_id, LocalDate created_at, LocalDate updated_at) {
		this.id = id;
		this.name = name;
		this.surface = surface;
		this.price = price;
		this.picture = picture;
		this.description = description;
		this.owner_id = owner_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public RentalResponseDto() {

	}
}
