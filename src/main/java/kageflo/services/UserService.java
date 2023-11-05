package kageflo.services;

import kageflo.entities.User;
import kageflo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface UserService {
    public User addUser(User user);

    public boolean authenticate(String username, String password);

    public List<User> getUsersByFields(Integer id,
                                       String username,
                                       String email,
                                       String dob,
                                       Boolean isAdmin);

    public List<User> getAllStudents(boolean sorted);
}
