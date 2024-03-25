package Pfe.module.users.controllers;


import Pfe.module.users.dto.SuccessResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/success")
public class SuccessController {

    @GetMapping
    public SuccessResponse hello(){
        return new SuccessResponse("Success Login");
    }
}
