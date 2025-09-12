package com.thomas.estate.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thomas.estate.dto.request.RentalRequestDto;
import com.thomas.estate.dto.response.ErrorResponseDto;
import com.thomas.estate.dto.response.ListRentalResponseDto;
import com.thomas.estate.dto.response.MessageResponseDto;
import com.thomas.estate.dto.response.RentalResponseDto;
import com.thomas.estate.service.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/rentals")
public class RentalController {

	@Autowired
	private RentalService rentalService;

	@PostMapping(path = "")
	@Operation(summary = "Publication d'une location")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Message de succès", content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Erreur de validation", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "401", description = "Non authorisé") })
	public ResponseEntity<?> postRental(@ModelAttribute @Valid RentalRequestDto request, Authentication authentication,
			BindingResult bindingResult) throws IOException {

		/** Réponse 400 si le requestBody n'est pas valide */
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getFieldError().getDefaultMessage();
			return ResponseEntity.badRequest().body(Map.of("Error", errorMessage));
		}

		try {
			MessageResponseDto response = rentalService.postRental(request, authentication);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponseDto(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
		}
	}

	@PutMapping(path = "/{id}")
	@Operation(summary = "Modification d'une location")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Message de succès", content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Erreur de validation", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "401", description = "Non authorisé") })
	public ResponseEntity<?> putRental(@PathVariable Integer id, @ModelAttribute @Valid RentalRequestDto request,
			Authentication authentication, BindingResult bindingResult) {

		/** Réponse 400 si le requestBody n'est pas valide */
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getFieldError().getDefaultMessage();
			return ResponseEntity.badRequest().body(Map.of("Error", errorMessage));
		}

		try {
			MessageResponseDto response = rentalService.putRental(request, authentication, id);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponseDto(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
		}
	}

	@GetMapping(path = "")
	@Operation(summary = "Récupération de la liste des location en ligne")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Liste des locations en ligne", content = @Content(schema = @Schema(implementation = ListRentalResponseDto.class))),
			@ApiResponse(responseCode = "401", description = "Non authorisé") })
	public ResponseEntity<ListRentalResponseDto> getRentals() {
		ListRentalResponseDto reponse = rentalService.getRentals();
		return ResponseEntity.ok(reponse);
	}

	@GetMapping(path = "/{id}")
	@Operation(summary = "Récupération des informations d'une location selon son ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Message de succès", content = @Content(schema = @Schema(implementation = RentalResponseDto.class))),
			@ApiResponse(responseCode = "401", description = "Non authorisé") })
	public ResponseEntity<?> getRental(@PathVariable Integer id) {
		try {
			RentalResponseDto reponse = rentalService.getRental(id);
			return ResponseEntity.ok(reponse);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponseDto(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
		}
	}
}
