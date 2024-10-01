package com.example.service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public int deleteMessageById(Integer messageId) {
        if (messageRepository.existsById(messageId)) {
            return messageRepository.deleteMessageById(messageId);
        }
        return 0;
    }

    public Message updateMessage(Integer id, Message message) {
        if (messageRepository.existsById(id)) {

            if (message.getMessageText().isEmpty()) {
                throw new IllegalArgumentException("Message text cannot be empty.");
            }

            if (message.getMessageText().length() > 255) {
                throw new IllegalArgumentException("Message text exceeds the allowable limit.");
            }

            return messageRepository.save(message);
        }
        return null; 
    }

    public List<Message> getAllMessagesForUser(Integer userId) {
        return messageRepository.findAllByPostedBy(userId);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        return optionalMessage.orElse(null);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
