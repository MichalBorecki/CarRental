package pl.coderslab.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
//	@Autowired
//	private UserRepository userRepo;

	// get all data for home page
	@GetMapping("")
	public String home(Model model) {
		return "home";
	}



	// model

	
}
