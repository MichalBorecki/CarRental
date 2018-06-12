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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.coderslab.carrental.bean.LoginData;
import pl.coderslab.carrental.bean.SessionManager;
import pl.coderslab.carrental.entity.Rent;
import pl.coderslab.carrental.entity.User;
import pl.coderslab.carrental.repository.RentRepository;
import pl.coderslab.carrental.repository.UserRepository;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "user" })
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RentRepository rentRepo;

	/*
	 * Register
	 */
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
		this.userRepo.save(user);
		model.addAttribute("user", user);
		return "redirect:/";

	}

	/*
	 * Log in
	 */
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
			if (user.getId() == 1) {
				return "redirect:/car/all";
			}
			redAttr.addFlashAttribute("message", "You are logged!");
			return "redirect:/";
		}
		model.addAttribute("message", "Please enter correct data!");
		return "user/login";
	}

	/*
	 * Logout
	 */
	@GetMapping("/logout")
	public String logout(SessionStatus status, Model model, HttpServletRequest request) {
		status.setComplete();
		model.addAttribute("loginData", new LoginData());
		return "redirect:/";
	}

	/*
	 * Update
	 */
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
	
	/*
	 * Activate user
	 */
	@PostMapping("/activate")
	public String changeStatus(@ModelAttribute User user, Model model) {

		this.userRepo.save(user);
		model.addAttribute("user", user);
		return "user/showUser";
	}

	/*
	 * Show all informations about user (by ID)
	 */
	@GetMapping("/info/{id}")
	public String showUserById(@PathVariable Long id, Model model) {
		User user = userRepo.findOne(id);
		model.addAttribute("user", user);
		
		
		model.addAttribute("rents", rentRepo.findAllByUserIdWhereEndIsNotNullOrderByStart(user.getId()));
		
		
		Rent rent = rentRepo.findRentByUserIdWhereEndIsNull(user.getId());
		model.addAttribute("rent", rent);	
		
		return "user/showUser";
	}

	/*
	 * Find form
	 */
	@GetMapping("/find")
	public String findForm(Model model) {
		return "user/findUser";
	}

	/*
	 * Find by term
	 */
	@GetMapping("/findterm{term}")
	public String findByTerm(@RequestParam("term") String term, Model model, HttpServletRequest request) {
		System.out.println(term);
		String termCorrected = term.replace("+", " ");
		System.out.println(termCorrected);
		List<User> usersFound = userRepo.findUserLike(termCorrected);
		System.out.println(usersFound);
		if (usersFound.equals(null)) {
			model.addAttribute("msg", "Nie znaleziono użtykownika dla frazy: " + termCorrected);
			return "user/findUser";
		}
		model.addAttribute("users", usersFound);
		return "user/showList";
	}

	/*
	 * Model
	 */
	@ModelAttribute("users")
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

}
