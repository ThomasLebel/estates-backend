package com.thomas.estate.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thomas.estate.dto.request.LoginRequestDto;
import com.thomas.estate.dto.request.RegisterRequestDto;
import com.thomas.estate.dto.response.ErrorResponseDto;
import com.thomas.estate.dto.response.LoginResponseDto;
import com.thomas.estate.dto.response.UserInfoResponseDto;
import com.thomas.estate.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping(path = "/register")
	@SecurityRequirements
	@Operation(summary = "Inscription et génération de token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Succès de connexion", content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Erreur de validation", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "401", description = "Non authorisé") })
	public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDto userRegisterDto,
			BindingResult bindingResult) {

		/** Réponse 400 si le requestBody n'est pas valide */
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getFieldError().getDefaultMessage();
			return ResponseEntity.badRequest().body(Map.of("Error", errorMessage));
		}
		try {
			LoginResponseDto response = authService.register(userRegisterDto);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponseDto(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
		}
	}

	@PostMapping(path = "/login")
	@SecurityRequirements
	@Operation(summary = "Authentification et génération de token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Succès de connexion", content = @Content(schema = @Schema(implementation = LoginResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Erreur de validation", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "401", description = "Non authorisé") })
	public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto, BindingResult bindingResult) {

		/** Réponse 400 si le requestBody n'est pas valide */
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getFieldError().getDefaultMessage();
			return ResponseEntity.badRequest().body(Map.of("Error", errorMessage));
		}
		try {
			LoginResponseDto response = authService.login(loginRequestDto);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponseDto(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
		}
	}

	@GetMapping(path = "/me")
	@Operation(summary = "Récupération informations utilisateur selon un ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Informations de l'utilisateur", content = @Content(schema = @Schema(implementation = UserInfoResponseDto.class))),
			@ApiResponse(responseCode = "401", description = "Non authorisé") })
	public ResponseEntity<?> getMe(Authentication authentication) {

		try {
			UserInfoResponseDto response = authService.getMe(authentication);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponseDto(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
		}
	}

}
