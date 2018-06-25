package pl.coderslab.carrental.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.coderslab.carrental.persistence.dao.UserRepository;
import pl.coderslab.carrental.persistence.model.User;

@Component
public class MySessionInfo {

    @Autowired
    private UserRepository userRepo;

    private User user;

    public long getUserId() {
        if (user == null) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email = userDetails.getUsername();
            user = userRepo.findByEmail(email);
        }
        return user.getId();
    }
}