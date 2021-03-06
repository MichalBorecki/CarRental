package pl.coderslab.carrental.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import pl.coderslab.carrental.persistence.model.Rent;

@Repository("rentRepository")
public interface RentRepository extends JpaRepository<Rent, Long> {
	
	@Query("SELECT r FROM Rent r WHERE r.user.id LIKE :id AND r.end IS NOT NULL ORDER BY r.start ASC")
	List<Rent> findAllByUserIdWhereEndIsNotNullOrderByStart(@Param("id") long id);

	@Query("SELECT r FROM Rent r WHERE r.user.id LIKE :id AND r.end IS NULL ORDER BY r.start ASC")
	Rent findRentByUserIdWhereEndIsNull(@Param("id") long id);
	
	@Query("SELECT r FROM Rent r WHERE r.end IS NULL ORDER BY r.start DESC")
	List<Rent> findAllWhereEndIsNull();
	
	@Query("SELECT r FROM Rent r WHERE r.end IS NOT NULL ORDER BY r.start DESC")
	List<Rent> findAllWhereEndIsNotNull();
	
}
