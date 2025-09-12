package com.thomas.estate.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.thomas.estate.dto.request.RentalRequestDto;
import com.thomas.estate.dto.response.ListRentalResponseDto;
import com.thomas.estate.dto.response.MessageResponseDto;
import com.thomas.estate.dto.response.RentalResponseDto;
import com.thomas.estate.model.Rental;
import com.thomas.estate.model.User;
import com.thomas.estate.repository.RentalRepository;
import com.thomas.estate.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RentalService {
	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Cloudinary cloudinary;

	public MessageResponseDto postRental(RentalRequestDto request, Authentication authentication) throws IOException {
		String email = authentication.getName();
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		Map uploadResult = cloudinary.uploader().upload(request.getPicture().getBytes(),
				ObjectUtils.asMap("folder", "rentals"));
		String imageUrl = uploadResult.get("secure_url").toString();
		Integer userID = user.get().getId();
		Rental rentalToAdd = new Rental(request.getName(), request.getSurface(), request.getPrice(), imageUrl,
				request.getDescription(), userID);
		rentalRepository.save(rentalToAdd);
		return new MessageResponseDto("Rental Created !");
	}

	public MessageResponseDto putRental(RentalRequestDto request, Authentication authentication, Integer ID) {
		String email = authentication.getName();
		log.info(email);
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		Optional<Rental> optionnalRentalToUpdate = rentalRepository.findById(ID);
		if (optionnalRentalToUpdate.isEmpty()) {
			throw new RuntimeException("Rental not found");
		} else if (!optionnalRentalToUpdate.get().getOwnerID().equals(user.get().getId())) {
			throw new RuntimeException("Not the owner");
		}
		Rental rentalToUpdate = optionnalRentalToUpdate.get();
		rentalToUpdate.updateRental(request.getName(), request.getSurface(), request.getPrice(),
				request.getDescription());
		rentalRepository.save(rentalToUpdate);
		return new MessageResponseDto("Rental Updated !");
	}

	public ListRentalResponseDto getRentals() {
		Iterable<Rental> iterableRentals = rentalRepository.findAll();
		List<RentalResponseDto> rentals = new ArrayList<>();
		for (Rental rental : iterableRentals) {
			LocalDate createdAt = rental.getCreatedAt().toLocalDate();
			LocalDate updatedAt = rental.getUpdatedAt().toLocalDate();
			RentalResponseDto rentalDto = new RentalResponseDto(rental.getId(), rental.getName(), rental.getSurface(),
					rental.getPrice(), rental.getPicture(), rental.getDescription(), rental.getOwnerID(), createdAt,
					updatedAt);
			rentals.add(rentalDto);
		}
		return new ListRentalResponseDto(rentals);
	}

	public RentalResponseDto getRental(Integer id) {
		Optional<Rental> optionnalRental = rentalRepository.findById(id);
		if (optionnalRental.isEmpty()) {
			throw new RuntimeException("Rental not found");
		}
		Rental rental = optionnalRental.get();
		LocalDate createAt = rental.getCreatedAt().toLocalDate();
		LocalDate updatedAt = rental.getUpdatedAt().toLocalDate();
		return new RentalResponseDto(rental.getId(), rental.getName(), rental.getSurface(), rental.getPrice(),
				rental.getPicture(), rental.getDescription(), rental.getOwnerID(), createAt, updatedAt);
	}
}
