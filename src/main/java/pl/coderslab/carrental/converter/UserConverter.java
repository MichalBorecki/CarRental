package pl.coderslab.carrental.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.carrental.persistence.dao.UserRepository;
import pl.coderslab.carrental.persistence.model.User;

public class UserConverter implements Converter<String, User> {

	@Autowired 
	private UserRepository userRepo;

	public User convert(String id) {
		return this.userRepo.findOne(Long.parseLong(id));
	}
}
