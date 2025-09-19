package com.thomas.estate.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "rentals")
@Data
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private BigDecimal surface;

	private BigDecimal price;

	private String picture;

	private String description;

	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	private User owner;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public void updateRental(String name, BigDecimal surface, BigDecimal price, String description) {
		this.name = name;
		this.surface = surface;
		this.price = price;
		this.description = description;
		this.updatedAt = LocalDateTime.now();
	}

	public Rental(String name, BigDecimal surface, BigDecimal price, String picture, String description, User owner) {
		this.name = name;
		this.surface = surface;
		this.price = price;
		this.picture = picture;
		this.description = description;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		this.owner = owner;

	}

	public Rental() {
	}
}
