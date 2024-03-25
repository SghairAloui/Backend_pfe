package Pfe.module.users.controllers;


import Pfe.module.users.Services.AuthService;
import Pfe.module.users.dto.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private final AuthService authService;

    public SignupController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {

        boolean isUserCreated = authService.createUser(signupRequest);

        if (isUserCreated) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Succefly to create customer");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer");
        }

    }
}
