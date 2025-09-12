package com.thomas.estate.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thomas.estate.dto.request.MessageRequestDto;
import com.thomas.estate.dto.response.ErrorResponseDto;
import com.thomas.estate.dto.response.MessageResponseDto;
import com.thomas.estate.service.MessageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/messages")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {
	@Autowired
	MessageService messageService;

	@PostMapping(path = "")
	@Operation(summary = "Publication d'un message")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Message créé", content = @Content(schema = @Schema(implementation = MessageResponseDto.class))),
			@ApiResponse(responseCode = "400", description = "Erreur de validation", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
			@ApiResponse(responseCode = "401", description = "Non authorisé") })

	public ResponseEntity<?> postMessage(@RequestBody @Valid MessageRequestDto request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getFieldError().getDefaultMessage();
			return ResponseEntity.badRequest().body(Map.of("Error", errorMessage));
		}
		try {
			MessageResponseDto reponse = messageService.postMessage(request);
			return ResponseEntity.ok(reponse);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErrorResponseDto(e.getMessage(), HttpStatus.UNAUTHORIZED.value()));
		}
	}
}
