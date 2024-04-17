package Pfe.module.users.Services;

import Pfe.module.users.Entity.Role;
import Pfe.module.users.Entity.User;
import Pfe.module.users.Repository.RoleRepository;
import Pfe.module.users.Repository.UserRepository;
import Pfe.module.users.dto.SignupRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
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

        Optional<Role> optionalRole = roleRepository.findByName("Admin");

        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            user.setRoles(Collections.singletonList(role));
            userRepository.save(user);
            return true;
        } else {
            // Handle case when role with name "USER" does not exist
            return false;
        }

    }
}
