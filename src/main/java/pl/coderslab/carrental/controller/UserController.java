package pl.coderslab.carrental.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.coderslab.carrental.bean.LoginData;
import pl.coderslab.carrental.bean.SessionManager;
import pl.coderslab.carrental.entity.User;
import pl.coderslab.carrental.repository.UserRepository;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "user" })
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "user/register";
	}

	@PostMapping("/register")
	public String registerPost(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "user/register";
		}
		user.setEnabled(true); // can be change in future by administrator
		this.userRepo.save(user);
		model.addAttribute("user", user);
		return "redirect:/";

	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginData", new LoginData());
		return "user/login";
	}

	@PostMapping("/login")
	public String loginPost(@ModelAttribute LoginData loginData,
		Model model,
		RedirectAttributes redAttr) {
		User user = this.userRepo.findOneByEmail(loginData.getEmail());

		if (user != null && user.isPasswordCorrect(loginData.getPassword())) {
			/*
			 * set user to session attribute
			 */
			HttpSession ses = SessionManager.session();
			ses.setAttribute("user", user);

			redAttr.addFlashAttribute("message", "You are logged!");
			return "redirect:/";
		}
		model.addAttribute("message", "Please enter correct data!");
		return "user/login";
	}

	@GetMapping("/logout")
	public String logout(SessionStatus status, Model model, HttpServletRequest request) {
		status.setComplete();
		model.addAttribute("loginData", new LoginData());
		return "redirect:/";
	}

	// update
	@GetMapping("/update/{id}")
	public String updateUser(@PathVariable Long id, Model model) {
		User user = userRepo.findOne(id);
		model.addAttribute("user", user);
		return "user/updateUser";
	}

	@PostMapping("/update")
	public String updateUserJsp(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/updateUser";
		}
		this.userRepo.save(user);
		model.addAttribute("user", user);
		return "redirect:/";
	}

	// Show user by ID
	@GetMapping("/{id}")
	public String showUserById(@PathVariable Long id, Model model) {
		User user = userRepo.findOne(id);
		model.addAttribute("user", user);
		return "user/showUser";
	}

	// find by term
	@GetMapping("/find/{term}")
	public String findByTerm(@PathVariable String term, Model model) {
		String termCorrected = term.replace("+", " ");
		List<User> usersFound = userRepo.findUserLike(termCorrected);
		model.addAttribute("usersFound", usersFound);
		return "user/showList";
	}

	// model
	@ModelAttribute("users")
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

}
