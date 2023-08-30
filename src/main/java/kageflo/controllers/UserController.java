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

    /**
     * Endpoint to add a new user.
     *
     * @param user The User object containing the details of the user to be added.
     * @return A ResponseEntity containing the added User object with a status of 201 Created.
     */
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User result = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /**
     * Endpoint to perform user authentication.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A ResponseEntity containing a boolean indicating whether the provided username
     *         and password combination exists in the database.
     */
    @GetMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam String username,
                                         @RequestParam String password) {
        Boolean response = userService.authenticate(username, password);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to retrieve users based on different fields using query parameters.
     *
     * @param id The unique identifier of the user.
     * @param username The username of the user.
     * @param email The email address of the user.
     * @param dob The date of birth of the user.
     * @param isAdmin Set to true to retrieve admin users only.
     * @return A ResponseEntity containing a list of User objects based on the provided query parameters.
     */
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

    /**
     * Endpoint to retrieve all users, optionally sorted by username.
     *
     * @param sorted Set to true to sort the users by username (default: false).
     * @return A list of all User objects, potentially sorted by username if requested.
     */
    @GetMapping(value = "/all")
    public List<User> getAllUsers(@RequestParam(defaultValue = "false", required = false) boolean sorted){
        return userService.getAllStudents(sorted);
    }
}
