package pl.coderslab.carrental.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.carrental.entity.Car;
import pl.coderslab.carrental.repository.CarRepository;

public class CarConverter implements Converter<String, Car> {

	@Autowired 
	private CarRepository carRepo;

	public Car convert(String id) {
		return this.carRepo.findOne(Long.parseLong(id));
	}
}
