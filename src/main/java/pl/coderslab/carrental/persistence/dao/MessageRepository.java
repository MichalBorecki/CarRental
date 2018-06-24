package pl.coderslab.carrental.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import pl.coderslab.carrental.persistence.model.Message;

@Repository("messageRepository")
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findAllByUserIdOrderByCreatedDesc(Long id);
	
	@Query("SELECT m FROM Message m WHERE m.user.id LIKE :id OR m.receiver.id LIKE :id ORDER BY m.created DESC")
	List<Message> findAllByUserIdOrReceiverId(@Param("id") long id);


}
