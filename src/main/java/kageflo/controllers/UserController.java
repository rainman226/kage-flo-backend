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

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User result = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/all")
    public List<User> getAllUsers(@RequestParam(defaultValue = "false", required = false) boolean sorted){
        return userService.getAllStudents(sorted);
    }
}
