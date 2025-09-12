package com.thomas.estate.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thomas.estate.dto.request.LoginRequestDto;
import com.thomas.estate.dto.request.RegisterRequestDto;
import com.thomas.estate.dto.response.LoginResponseDto;
import com.thomas.estate.dto.response.UserInfoResponseDto;
import com.thomas.estate.model.User;
import com.thomas.estate.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private JWTService jwtService;

	public LoginResponseDto register(RegisterRequestDto registerRequestDto) {
		Optional<User> existingUser = userRepository.findByEmail(registerRequestDto.getEmail());
		if (existingUser.isPresent()) {
			throw new RuntimeException("Email already registered");
		}
		String hashPassword = bcrypt.encode(registerRequestDto.getPassword());
		User newUser = new User(registerRequestDto.getName(), registerRequestDto.getEmail(), hashPassword);

		userRepository.save(newUser);

		String token = jwtService.generateToken(registerRequestDto.getEmail());
		return new LoginResponseDto(token);
	}

	public LoginResponseDto login(LoginRequestDto loginRequestDto) {
		Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());
		if (user.isEmpty() || !bcrypt.matches(loginRequestDto.getPassword(), user.get().getPassword())) {
			throw new RuntimeException("Email not found or wrong password");
		}
		String token = jwtService.generateToken(loginRequestDto.getEmail());
		return new LoginResponseDto(token);
	}

	public UserInfoResponseDto getMe(Authentication authentication) {
		String email = authentication.getName();
		Optional<User> optionnalUser = userRepository.findByEmail(email);
		if (optionnalUser.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		User user = optionnalUser.get();
		LocalDate createdAt = user.getCreatedAt().toLocalDate();
		LocalDate updatedAt = user.getUpdatedAt().toLocalDate();
		return new UserInfoResponseDto(user.getId(), user.getName(), user.getEmail(), createdAt, updatedAt);
	}
}
