package pl.coderslab.carrental.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.carrental.persistence.model.User;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findUserById(long id);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:term% OR u.lastName LIKE %:term% OR u.email LIKE %:term%")
    List<User> findUserLike(@Param("term") String term);
}
