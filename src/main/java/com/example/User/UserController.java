package com.example.User;

import com.example.User.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(
                user.getId(),        // int
                user.getusername(),  // String
                user.getpassword(),  // String
                user.getemail(),     // String
                user.getCreatedAt()  // LocalDateTime
        );
        System.out.println("Newly Created Schedule ID: " + createdUser.getId());
        return ResponseEntity.ok(createdUser);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getuserById(@PathVariable int id) {
        return ResponseEntity.ok(userService.getuserById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteuser(@PathVariable int id, @RequestParam String password) {
        userService.deleteuser(id, password);
        return ResponseEntity.noContent().build();
    }


}
