package Pfe.module.users.dto;

public class LoginResponse {

     public String jwtToken;

    public LoginResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
