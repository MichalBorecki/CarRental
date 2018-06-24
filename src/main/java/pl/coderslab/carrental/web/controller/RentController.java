package pl.coderslab.carrental.web.controller;

import java.time.LocalDateTime;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.carrental.persistence.dao.UserRepository;
import pl.coderslab.carrental.persistence.model.User;
import pl.coderslab.carrental.persistence.model.Car;
import pl.coderslab.carrental.persistence.model.Rent;
import pl.coderslab.carrental.persistence.dao.CarRepository;
import pl.coderslab.carrental.persistence.dao.RentRepository;

@Controller
@RequestMapping("/rent")
public class RentController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RentRepository rentRepo;

	@Autowired
	private CarRepository carRepo;

	@PostMapping("/start")
	public String formPost(@Valid Rent rent, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "car/actionsCar";
		}
		/*
		 * Set user to car and save
		 */
		Car car = carRepo.findOne(rent.getCar().getId());
		car.setUser(rent.getUser());
		carRepo.save(car);
		/*
		 * Save rent
		 */
		rentRepo.save(rent);
		model.addAttribute("rent", rent);
		return "redirect:/rent/current";
	}

	/*
	 * Convert java.time.LocalDateTime to java.util.Date
	 */
	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
	    return java.sql.Timestamp.valueOf(dateToConvert);
	}

	@PostMapping("/end")
	public String form(@RequestParam String id, Model model, HttpServletRequest request) {
		long rentId = Long.parseLong(id);
		Rent rentForEnd = rentRepo.findOne(rentId);
		System.out.println(rentForEnd);
		/*
		 * Set the time to finish renting
		 */
		LocalDateTime now = LocalDateTime.now();
		Date date = convertToDateViaSqlTimestamp(now);
		rentForEnd.setEnd(date);


		/*
		 *  for simplicity check rent time and set distance as one minute is 500 m
		 *  distance covered (low average speed in big city like Wrocław)
		 */
		long rentTime = rentForEnd.getRentTime();
		double distance = rentTime * 0.5;
		rentForEnd.setDistance(distance);

		/*
		 * Set user car for null, and set lat i lng of end of ride.
		 */
		Car car = carRepo.findOne(rentForEnd.getCar().getId());
		car.setUser(null);
		car.setMileage(car.getMileage() + distance);
		car.setLat(rentForEnd.getLatEnd());
		car.setLng(rentForEnd.getLngEnd());
		carRepo.save(car);
		
		rentRepo.save(rentForEnd);
		model.addAttribute("rents", rentRepo.findAllByUserIdWhereEndIsNotNullOrderByStart(rentForEnd.getUser().getId()));
		return "rent/listRent";
	}

	/*
	 * All rents
	 */
	@GetMapping("/all")
	public String findAllFree(Model model, HttpServletRequest request) {
		model.addAttribute("rents", rentRepo.findAllWhereEndIsNotNull());
		return "rent/listRent";
	}

	/*
	 * All rents by userId
	 */
	@GetMapping("/all/user")
	public String findAllRentedByUserId(Model model, HttpServletRequest request) {

		HttpSession sess = request.getSession();
		User user = (User) sess.getAttribute("user");

		model.addAttribute("rents", rentRepo.findAllByUserIdWhereEndIsNotNullOrderByStart(user.getId()));
		return "rent/listRent";
	}
	
	/*
	 * All rents by userId, redirect to charges view
	 */
	@GetMapping("/charges")
	public String findAllRentedByUserIdForChargesView(Model model, HttpServletRequest request) {

		HttpSession sess = request.getSession();
		User user = (User) sess.getAttribute("user");

		model.addAttribute("rents", rentRepo.findAllByUserIdWhereEndIsNotNullOrderByStart(user.getId()));
		return "rent/chargesRent";
	}
	
	/*
	 * All rents by userId (for ajax)
	 */
	@GetMapping("/all/userInfoChart/{id}")
	@ResponseBody
	public List<Rent> findAllRentedByUserId(@PathVariable long id, Model model, HttpServletRequest request) {
		List<Rent> list = rentRepo.findAllByUserIdWhereEndIsNotNullOrderByStart(id);
		return list;
	}

	/*
	 * Find current rental by user
	 */
	@GetMapping("/current")
	public String findRentByUserId(Model model, HttpServletRequest request) {

		HttpSession sess = request.getSession();
		User user = (User) sess.getAttribute("user");

		Rent rent = rentRepo.findRentByUserIdWhereEndIsNull(user.getId());
		model.addAttribute("rent", rent);
		return "car/currentRented";
	}

	/*
	 * Model
	 */
	@ModelAttribute("rents")
	public List<Rent> getRents() {
		return this.rentRepo.findAll();
	}

	@ModelAttribute("users")
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

}
