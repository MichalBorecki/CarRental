package pl.coderslab.carrental.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.carrental.entity.Rent;
import pl.coderslab.carrental.repository.RentRepository;

public class RentConverter implements Converter<String, Rent> {

	@Autowired 
	private RentRepository rentRepo;

	public Rent convert(String id) {
		return this.rentRepo.findOne(Long.parseLong(id));
	}
}
