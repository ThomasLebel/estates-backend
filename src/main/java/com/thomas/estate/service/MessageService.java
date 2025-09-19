package com.thomas.estate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomas.estate.dto.request.MessageRequestDto;
import com.thomas.estate.dto.response.MessageResponseDto;
import com.thomas.estate.model.Message;
import com.thomas.estate.model.Rental;
import com.thomas.estate.model.User;
import com.thomas.estate.repository.MessageRepository;
import com.thomas.estate.repository.RentalRepository;
import com.thomas.estate.repository.UserRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private UserRepository userRepository;

	public MessageResponseDto postMessage(MessageRequestDto request) {
		Rental rental = rentalRepository.findById(request.getRental_id())
				.orElseThrow(() -> new RuntimeException("Rental not found"));
		User user = userRepository.findById(request.getUser_id())
				.orElseThrow(() -> new RuntimeException("User not found"));

		Message messageToAdd = new Message(rental, user, request.getMessage());
		messageRepository.save(messageToAdd);
		return new MessageResponseDto("Message send with success");
	}

}
