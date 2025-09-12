package com.thomas.estate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomas.estate.dto.request.MessageRequestDto;
import com.thomas.estate.dto.response.MessageResponseDto;
import com.thomas.estate.model.Message;
import com.thomas.estate.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public MessageResponseDto postMessage(MessageRequestDto request) {
		Message messageToAdd = new Message(request.getRental_id(), request.getUser_id(), request.getMessage());
		messageRepository.save(messageToAdd);
		return new MessageResponseDto("Message send with success");
	}

}
