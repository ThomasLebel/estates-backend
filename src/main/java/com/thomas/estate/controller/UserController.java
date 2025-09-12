package com.thomas.estate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thomas.estate.dto.response.ErrorResponseDto;
import com.thomas.estate.dto.response.UserInfoResponseDto;
import com.thomas.estate.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path = "api/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(path = "/{id}")
	@Operation(summary = "Récupération des informations d'un utilisateur selon un ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Informations utilisateur", content = @Content(schema = @Schema(implementation = UserInfoResponseDto.class))),
			@ApiResponse(responseCode = "401", description = "Non authorisé") })
	public ResponseEntity<?> getUserInfos(@PathVariable Integer id) {
		try {
			UserInfoResponseDto reponse = userService.getUserInfos(id);
			return ResponseEntity.ok(reponse);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponseDto(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
		}
	}
}
