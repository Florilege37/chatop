package com.chatop.chatop;

import com.chatop.chatop.service.MessageServiceImpl;
import com.chatop.chatop.service.RentalsServiceImpl;
import com.chatop.chatop.service.UserServiceImpl;
import com.chatop.chatop.service.interfaces.MessageService;
import com.chatop.chatop.service.interfaces.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatopApplication {
	public static void main(String[] args) {
		SpringApplication.run(ChatopApplication.class, args);
	}

}
