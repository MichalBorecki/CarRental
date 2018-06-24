package pl.coderslab.carrental.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import pl.coderslab.carrental.persistence.model.Car;

@Repository("carRepository")
public interface CarRepository extends JpaRepository<Car, Long> {

	List<Car> findByUserIsNull();

	List<Car> findByUserIsNotNull();

	Car findCarByUserId(Long id);

}
