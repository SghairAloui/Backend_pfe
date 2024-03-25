package Pfe.module.users.Services;

import Pfe.module.users.Entity.User;
import Pfe.module.users.Repository.UserRepository;
import Pfe.module.users.dto.SignupRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createUser(SignupRequest signupRequest) {

        if (UserRepository.existByEmail(signupRequest.getEmail())) {
            return false;
        }

        User user = new User();
        BeanUtils.copyProperties(signupRequest,user);

        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashPassword);
        userRepository.save(user);
        return true;
    }
}
