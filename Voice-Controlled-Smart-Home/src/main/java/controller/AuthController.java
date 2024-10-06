package controller;

import dto.LoginRequest;
import model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import service.AuthService;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;  // For JWT generation

    @Autowired
    private PasswordEncoder passwordEncoder;

    // User Registration
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.createUser(user));
    }

    // User Login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request) {
        User user = userService.findByUsername(request.getUsername());

        // Validate user and password
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            // Generate JWT token
            String token = authService.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
