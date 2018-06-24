package pl.coderslab.carrental.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import pl.coderslab.carrental.persistence.dao.UserRepository;
import pl.coderslab.carrental.persistence.model.User;
import pl.coderslab.carrental.persistence.model.Rent;
import pl.coderslab.carrental.persistence.dao.RentRepository;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "user" })
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RentRepository rentRepo;


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
