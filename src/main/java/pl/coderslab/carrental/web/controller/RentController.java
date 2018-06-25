package pl.coderslab.carrental.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.carrental.component.MySessionInfo;
import pl.coderslab.carrental.persistence.dao.CarRepository;
import pl.coderslab.carrental.persistence.dao.RentRepository;
import pl.coderslab.carrental.persistence.dao.UserRepository;
import pl.coderslab.carrental.persistence.model.Car;
import pl.coderslab.carrental.persistence.model.Rent;
import pl.coderslab.carrental.persistence.model.User;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/rent")
public class RentController {

	@Autowired
	private MySessionInfo mySessionInfo;

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
	public String form(@RequestParam String id, Model model) {
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
		 *  For simplicity check rent time and set distance as one minute is 500 m
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
	@Secured("hasRole('ADMIN')")
	@GetMapping("/all")
	public String findAllFree(Model model) {
		model.addAttribute("rents", rentRepo.findAllWhereEndIsNotNull());
		return "rent/listRent";
	}

	/*
	 * All rents by userId
	 */
	@GetMapping("/all/user")
	public String findAllRentedByUserId(Model model) {

		model.addAttribute("rents", rentRepo.findAllByUserIdWhereEndIsNotNullOrderByStart(mySessionInfo.getUserId()));
		return "rent/listRent";
	}
	
	/*
	 * All rents by userId, redirect to charges view
	 */
	@GetMapping("/charges")
	public String findAllRentedByUserIdForChargesView(Model model) {
		model.addAttribute("rents", rentRepo.findAllByUserIdWhereEndIsNotNullOrderByStart(mySessionInfo.getUserId()));
		return "rent/chargesRent";
	}
	
	/*
	 * All rents by userId (for ajax)
	 */
	@GetMapping("/all/userInfoChart")
	@ResponseBody
	public List<Rent> findAllRentedByUserId() {
		List<Rent> list = rentRepo.findAllByUserIdWhereEndIsNotNullOrderByStart(mySessionInfo.getUserId());
		return list;
	}

	/*
	 * Find current rental by user
	 */
	@GetMapping("/current")
	public String findRentByUserId(Model model) {

		Rent rent = rentRepo.findRentByUserIdWhereEndIsNull(mySessionInfo.getUserId());
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
