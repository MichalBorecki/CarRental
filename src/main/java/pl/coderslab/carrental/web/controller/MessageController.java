package pl.coderslab.carrental.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.coderslab.carrental.persistence.dao.UserRepository;
import pl.coderslab.carrental.persistence.model.User;
import pl.coderslab.carrental.persistence.model.Message;
import pl.coderslab.carrental.persistence.dao.MessageRepository;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MessageRepository messageRepo;

	/*
	 * Add message to admin
	 */
	@GetMapping("/add")
	public String form(Model model, HttpServletRequest request) {
		Message message = new Message();

		// Message will be send to admin so we have to set receiver as admin
		User admin = userRepo.findOne((long) 1);
		message.setReceiver(admin);

		model.addAttribute("message", message);
		request.setAttribute("msg", "CarRental");
		return "message/addMessageForm";
	}

	@PostMapping("/add")
	public String formPost(@Valid Message message, BindingResult result, Model model) {
		System.out.println(message);
		if (result.hasErrors()) {
			return "message/addMessageForm";
		}
		/*
		 * If user is Admin, then redirect to Admin list of messages
		 */
		if (message.getUser().getId() == 1) {
			return "redirect:/message/adminlist/2";
		}
		messageRepo.save(message);
		return "redirect:/message/all";
	}

	/*
	 * Add message to user of this ID
	 */
	@GetMapping("/add/{userId}")
	public String formAddMessageToUser(@PathVariable long userId, Model model, HttpServletRequest request) {
		Message message = new Message();

		User receiver = userRepo.findUserById(userId);
		request.setAttribute("msg", receiver.getFullName());
		message.setReceiver(receiver);

		model.addAttribute("message", message);
		return "message/addMessageForm";
	}

	/*
	 * All messages sent and received by this user
	 */
	@GetMapping("/all")
	public String findAll(Model model, HttpServletRequest request) {

		HttpSession sess = request.getSession();
		User user = (User) sess.getAttribute("user");

		List<Message> messages = messageRepo.findAllByUserIdOrReceiverId(user.getId());

		model.addAttribute("messages", messages);

		// TODO
		// // When ifRead is false and message was sent by user, then change for true
		// for (Message message : messages) {
		// if (message.getReceiver().getId() == (user.getId()) && message.isIfRead() ==
		// false) {
		// message.setIfRead(true);
		// messageRepo.save(message);
		// }
		// }

		return "message/listMessage";
	}

	/*
	 * All emails from admin to this user
	 */
	@Secured("hasRole('ADMIN')")
	@GetMapping("/adminlist/{userId}")
	public String findAllMsgBetweenAdminAndUser(@PathVariable long userId, Model model) {
		List<Message> messages = messageRepo.findAllByUserIdOrReceiverId(userId);
		User sender = userRepo.findOne(userId);
		model.addAttribute("sender", sender);
		model.addAttribute("messages", messages);
		return "message/listMessageAdmin";
	}

	/*
	 * Model
	 */
	@ModelAttribute("messages")
	public List<Message> getMessages() {
		return this.messageRepo.findAll();
	}

	@ModelAttribute("users")
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

}
