package pl.coderslab.carrental.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import pl.coderslab.carrental.entity.User;
import pl.coderslab.carrental.repository.UserRepository;

public class UserConverter implements Converter<String, User> {

	@Autowired 
	private UserRepository userRepo;

	public User convert(String id) {
		return this.userRepo.findOne(Long.parseLong(id));
	}
}
