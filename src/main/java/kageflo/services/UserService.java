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

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword); // Set the hashed password
        return userRepository.save(user);
    }

    public boolean authenticate(String username, String password){
        User user = userRepository.findByUsername(username);
        if(user != null){
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
    public List<User> getUsersByFields(Integer id,
                                       String username,
                                       String email,
                                       String dob,
                                       Boolean isAdmin) {
        Specification<User> spec = Specification.where(null);

        if(id != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id));
        }

        if (username != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), username));
        }

        if (email != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email));
        }

        if (dob != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("dob"), dob));
        }

        if (isAdmin != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isAdmin"), isAdmin));
        }

        List<User> users = userRepository.findAll(spec);

        if (users.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return userRepository.findAll(spec);
    }

    public List<User> getAllStudents(boolean sorted) {
        List<User> users;
        if(sorted) users = userRepository.findAllByOrderByUsernameAsc();
        else users = userRepository.findAll();
        return users;
    }
}
