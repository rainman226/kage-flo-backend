package kageflo.controllers;

import kageflo.entities.User;
import kageflo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User result = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @CrossOrigin(origins = "http://localhost:3000")
     @GetMapping
    public ResponseEntity<List<User>> getUsersByFields(@RequestParam(required = false) Integer id,
                                                       @RequestParam(required = false) String username,
                                                       @RequestParam(required = false) String email,
                                                       @RequestParam(required = false) String dob,
                                                       @RequestParam(required = false) Boolean isAdmin) {

        List<User> users = userService.getUsersByFields(id, username, email, dob, isAdmin);

        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/all")
    public List<User> getAllUsers(@RequestParam(defaultValue = "false", required = false) boolean sorted){
        return userService.getAllStudents(sorted);
    }
}
