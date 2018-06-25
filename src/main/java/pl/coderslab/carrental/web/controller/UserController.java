package pl.coderslab.carrental.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.carrental.component.MySessionInfo;
import pl.coderslab.carrental.persistence.dao.RentRepository;
import pl.coderslab.carrental.persistence.dao.UserRepository;
import pl.coderslab.carrental.persistence.model.Rent;
import pl.coderslab.carrental.persistence.model.User;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "user" })
public class UserController {

	@Autowired
	private MySessionInfo mySessionInfo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RentRepository rentRepo;


	/*
	 * Update
	 */
	@GetMapping("/update")
	public String updateUser(Model model) {
		User user = userRepo.findOne(mySessionInfo.getUserId());
		model.addAttribute("user", user);
		return "user/updateUser";
	}

	@PostMapping("/update")
	public String updateUserJsp(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/updateUser";
		}
		user.setId(mySessionInfo.getUserId());
		this.userRepo.save(user);
		model.addAttribute("user", user);
		return "redirect:/";
	}
	
	/*
	 * Activate user
	 */
    @PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/activate/{id}")
	public String changeStatus(@PathVariable Long id, Model model) {
		User user = userRepo.findOne(id);
		if (user.getActive() == 0){
			user.setActive(1);
		} else if (user.getActive() == 1) {
			user.setActive(0);
		}
		this.userRepo.save(user);
		model.addAttribute("user", user);
		return "user/showUser";
	}

	/*
	 * Show all informations about user (by ID)
	 */
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/find")
	public String findForm(Model model) {
		return "user/findUser";
	}

	/*
	 * Find by term
	 */
    @PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/findterm{term}")
	public String findByTerm(@RequestParam("term") String term, Model model) {
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
