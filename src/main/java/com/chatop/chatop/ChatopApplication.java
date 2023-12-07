package com.chatop.chatop;

import com.chatop.chatop.entity.MessageDB;
import com.chatop.chatop.entity.UserDB;
import com.chatop.chatop.model.MessageModel;
import com.chatop.chatop.model.RentalsModel;
import com.chatop.chatop.model.UserModel;
import com.chatop.chatop.service.MessageService;
import com.chatop.chatop.service.RentalsService;
import com.chatop.chatop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatopApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private RentalsService rentalsService;

	public static void main(String[] args) {
		SpringApplication.run(ChatopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userService.delUsers();
		MessageModel mm = new MessageModel(11L,12L,"Superbe villa !", null, null);
		messageService.createMessage(mm);
		Iterable<UserDB> users = userService.getUsers();
		Iterable<MessageDB> messages = messageService.getMessages();
		users.forEach(user -> System.out.println(user.toString()));
		messages.forEach(message -> System.out.println(message.toString()));
		RentalsModel rm = new RentalsModel("Villa de Luxe",465, 1999, "Image", "Villa en bord de mer", 4L, null, null);
		rentalsService.createRental(rm);
	}
}
