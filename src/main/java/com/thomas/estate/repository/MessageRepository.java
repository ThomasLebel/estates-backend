package com.thomas.estate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thomas.estate.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

}
