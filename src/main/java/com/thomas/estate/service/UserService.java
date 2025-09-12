package com.thomas.estate.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomas.estate.dto.response.UserInfoResponseDto;
import com.thomas.estate.model.User;
import com.thomas.estate.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserInfoResponseDto getUserInfos(Integer id) {
		Optional<User> optionnalUser = userRepository.findById(id);
		if (optionnalUser.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		User user = optionnalUser.get();
		LocalDate createdAt = user.getCreatedAt().toLocalDate();
		LocalDate updatedAt = user.getUpdatedAt().toLocalDate();
		return new UserInfoResponseDto(id, user.getName(), user.getEmail(), createdAt, updatedAt);
	}

}
