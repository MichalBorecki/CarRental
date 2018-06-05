package pl.coderslab.carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.coderslab.carrental.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findAllByUserIdOrderByCreatedDesc(Long id);
	
	@Query("SELECT m FROM Message m WHERE m.user.id LIKE :id OR m.receiver.id LIKE :id ORDER BY m.created DESC")
	List<Message> findAllByUserIdOrReceiverId(@Param("id") long id);


}
