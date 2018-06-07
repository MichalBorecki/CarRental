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
import org.springframework.web.bind.annotation.ResponseBody;

import pl.coderslab.carrental.bean.SessionManager;
import pl.coderslab.carrental.entity.Car;
import pl.coderslab.carrental.entity.Rent;
import pl.coderslab.carrental.entity.User;
import pl.coderslab.carrental.repository.CarRepository;
import pl.coderslab.carrental.repository.RentRepository;
import pl.coderslab.carrental.repository.UserRepository;

@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CarRepository carRepo;

	@Autowired
	private RentRepository rentRepo;
	/*
	 * Add
	 */
	@GetMapping("/add")
	public String form(Model model) {
		Car car = new Car();
		model.addAttribute("car", car);
		return "car/addCarForm";
	}

	@PostMapping("/add")
	public String formPost(@Valid Car car, BindingResult result) {
		if (result.hasErrors()) {
			return "car/addCarForm";
		}
		carRepo.save(car);
		return "redirect:/car/all";
	}

	/*
	 *  Update
	 */
	@GetMapping("/update/{carId}")
	public String form(@PathVariable long carId, Model model, HttpServletRequest request) {
		Car car = carRepo.findOne(carId);
		model.addAttribute("car", car);
		request.setAttribute("msg", "Edycja danych:");
		return "car/addCarForm";
	}

	/*
	 *  Delete
	 */
	@GetMapping("/delete/{carId}")
	public String form(@PathVariable long carId) {
		Car car = carRepo.findOne(carId);
		carRepo.delete(car);
		return "redirect:/car/all";
	}

	
	/*
	 *  Car for rent
	 */
	@GetMapping("/rent/{carId}")
	public String findCarForRent(@PathVariable String carId, Model model, HttpServletRequest request) {
		/*
		 * Get user
		 */
		HttpSession sess = request.getSession();
		User user = (User) sess.getAttribute("user");
		long userId = user.getId();
		/*
		 * Check if user have active rent
		 */
		if (rentRepo.findRentByUserIdWhereEndIsNull(userId) != null) {
			Rent rent = rentRepo.findRentByUserIdWhereEndIsNull(userId);
			model.addAttribute("rent", rent);
			model.addAttribute("msg", "Nie możesz wypożyczyć kolejnego auta, napierm musisz oddać: ");
			return "car/currentRented";
		}
		
		/*
		 * Create new rent and set user
		 */
		Rent rent = new Rent();
		rent.setUser(user);
		/*
		 * Set car
		 */
		Car car = carRepo.findOne(Long.parseLong(carId));
		rent.setCar(car);
		System.out.println(car);
		model.addAttribute("rent", rent);
		return "car/actionsCar";
	}
	
	/*
	 *  All cars that are free
	 */
	@GetMapping("/allfree")
	public String findAllFree(Model model, HttpServletRequest request) {
		model.addAttribute("cars", carRepo.findByUserIsNull());
		request.setAttribute("msg", "Lista wolnych samochodów:");
		return "car/listCar";
	}

	/*
	 *  Function for finding free cars, returning list to body
	 */
	@GetMapping("/allFreeForMap")
	@ResponseBody
	public List<Car> findAllFreeForMap() {
		List<Car> cars = carRepo.findByUserIsNull();
		return cars;
	}

	/*
	 *  All cars rented
	 */
	@GetMapping("/allrented")
	public String findAllRented(Model model, HttpServletRequest request) {
		model.addAttribute("cars", carRepo.findByUserIsNotNull());
		request.setAttribute("msg", "Lista wypożyczonych samochodów:");
		return "car/listRented";
	}

	/*
	 *  All cars
	 */
	@GetMapping("/all")
	public String findAll(Model model, HttpServletRequest request) {
		model.addAttribute("cars", carRepo.findAll());
		request.setAttribute("msg", "Lista samochodów:");
		return "car/listCar";
	}

//	/*
//	 *  Find car actually rent by user
//	 */
//	@GetMapping("/current")
//	public String findRentedCarByUserId(Model model, HttpServletRequest request) {
//		System.out.println("====================");
//		HttpSession sess = request.getSession();
//		User user = (User) sess.getAttribute("user");
//		long id = user.getId();
//		System.out.println(id);
//		Rent rent = rentRepo.findRentByUserIdWhereEndIsNull(id);
//		System.out.println(rent + "-------------------");
//		model.addAttribute("rent", rent);
//		return "car/currentRented";
//	}

	/*
	 *  model
	 */
	@ModelAttribute("cars")
	public List<Car> getCars() {
		return this.carRepo.findAll();
	}

	@ModelAttribute("users")
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

}
