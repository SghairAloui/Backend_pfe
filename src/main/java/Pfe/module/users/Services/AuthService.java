package Pfe.module.users.Services;

import Pfe.module.users.Entity.User;
import Pfe.module.users.dto.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    boolean createUser(SignupRequest signupRequest);
}
