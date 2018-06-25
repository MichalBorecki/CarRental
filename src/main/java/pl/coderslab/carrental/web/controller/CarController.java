package pl.coderslab.carrental.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.carrental.component.MySessionInfo;
import pl.coderslab.carrental.persistence.dao.CarRepository;
import pl.coderslab.carrental.persistence.dao.MessageRepository;
import pl.coderslab.carrental.persistence.dao.RentRepository;
import pl.coderslab.carrental.persistence.dao.UserRepository;
import pl.coderslab.carrental.persistence.model.Car;
import pl.coderslab.carrental.persistence.model.Message;
import pl.coderslab.carrental.persistence.model.Rent;
import pl.coderslab.carrental.persistence.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

	@Autowired
	private MySessionInfo mySessionInfo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CarRepository carRepo;

	@Autowired
	private RentRepository rentRepo;

	@Autowired
	private MessageRepository messageRepo;

	/*
	 * Add
	 */
    @Secured("hasRole('ADMIN')")
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
	 * Update
	 */
    @Secured("hasRole('ADMIN')")
	@GetMapping("/update/{carId}")
	public String form(@PathVariable long carId, Model model, HttpServletRequest request) {
		Car car = carRepo.findOne(carId);
		model.addAttribute("car", car);
		request.setAttribute("msg", "Edycja danych:");
		return "car/addCarForm";
	}

	/*
	 * Delete
	 */
    @Secured("hasRole('ADMIN')")
	@GetMapping("/delete/{carId}")
	public String form(@PathVariable long carId) {
		Car car = carRepo.findOne(carId);
		carRepo.delete(car);
		return "redirect:/car/all";
	}

	/*
	 * Car for rent
	 */
	@GetMapping("/rent/{carId}")
	public String findCarForRent(@PathVariable String carId, Model model) {
		/*
		 * Get user
		 */
		User user = mySessionInfo.getCurrentUser();
		long userId = user.getId();
		
		if (user.getActive() == 0) {
			model.addAttribute("msg", "Nie możesz wypożyczyć samochodu, Twoje konto jest zdezaktywowane!");
			return "car/currentRented";
		}
		
		/*
		 * Check if user have active rent
		 */
		if (rentRepo.findRentByUserIdWhereEndIsNull(userId) != null) {
			Rent rent = rentRepo.findRentByUserIdWhereEndIsNull(userId);
			model.addAttribute("rent", rent);
			model.addAttribute("msg", "Nie możesz wypożyczyć kolejnego auta, najpierw musisz oddać: ");
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
	 * All cars that are free
	 */
    @Secured("hasRole('ADMIN')")
	@GetMapping("/allfree")
	public String findAllFree(Model model, HttpServletRequest request) {
		model.addAttribute("cars", carRepo.findByUserIsNull());
		request.setAttribute("msg", "Lista wolnych samochodów:");
		return "car/listCar";
	}

	/*
	 * All cars that are free on map - Admin view
	 */
    @Secured("hasRole('ADMIN')")
	@GetMapping("/mapAdmin")
	public String showFreeCarsOnMap() {
		return "car/mapFreeCarsAdmin";
	}

	/*
	 * Set car for service, redirect to add message to repairMan
	 */
    @Secured("hasRole('ADMIN')")
	@GetMapping("/service/{carId}")
	public String callServiceToCar(Model model, @RequestParam String lat, @RequestParam String lng,
		@PathVariable long carId) {

		Car car = carRepo.findOne(carId);

		User servisant = userRepo.findOne((long) 2);
		/*
		 * Set Service as car renter
		 */
		car.setUser(servisant);
		carRepo.save(car);
		/*
		 * Save rent
		 */
		Rent serviceRent = new Rent();
		serviceRent.setLatStart(Double.parseDouble(lat));
		serviceRent.setLngStart(Double.parseDouble(lng));
		serviceRent.setCar(car);
		serviceRent.setUser(servisant);
		rentRepo.save(serviceRent);

		Message messageToService = new Message();
		messageToService.setMessageText("Proszę zabrać samochód o numerze " + car.getId()
			+ " do serwisu. <br><a href=\"https://www.google.com/maps/search/?api=1&query="
			+ lat + "," + lng + "\">Lokalizacja samochodu</a>");
		messageToService.setReceiver(servisant);
		messageToService.setUser(userRepo.findOne((long) 1));
		messageToService.setUrgent(true);

		messageRepo.save(messageToService);
		Message messageForForm = messageRepo.findOne(messageToService.getId());
		model.addAttribute("message", messageForForm);
		return "message/addMessageForm";
	}

	/*
	 * Function for finding free cars, returning list to body
	 */
	@GetMapping("/allFreeForMap")
	@ResponseBody
	public List<Car> findAllFreeForMap() {
		List<Car> cars = carRepo.findByUserIsNull();
		return cars;
	}

	/*
	 * All cars rented
	 */
    @Secured("hasRole('ADMIN')")
	@GetMapping("/allrented")
	public String findAllRented(Model model, HttpServletRequest request) {
		model.addAttribute("cars", carRepo.findByUserIsNotNull());
		request.setAttribute("msg", "Lista wypożyczonych samochodów:");
		return "car/listRented";
	}

	/*
	 * All cars
	 */
    @Secured("hasRole('ADMIN')")
	@GetMapping("/all")
	public String findAll(Model model, HttpServletRequest request) {
		model.addAttribute("cars", carRepo.findAll());
		request.setAttribute("msg", "Lista samochodów:");
		return "car/listCar";
	}

	/*
	 * Model
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
