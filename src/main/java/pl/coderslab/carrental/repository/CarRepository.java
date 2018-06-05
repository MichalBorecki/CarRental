package pl.coderslab.carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.carrental.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	
	List<Car> findByUserIsNull();
	
	List<Car> findByUserIsNotNull();
	
	Car findCarByUserId(Long id);
	
}
