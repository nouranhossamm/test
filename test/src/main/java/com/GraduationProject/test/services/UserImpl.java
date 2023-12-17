package com.GraduationProject.test.services;

import com.GraduationProject.test.DTOs.LoginDTO;
import com.GraduationProject.test.DTOs.LoginMesage;
import com.GraduationProject.test.DTOs.UserDTO;
import com.GraduationProject.test.entities.AppUser;
import com.GraduationProject.test.repositories.UserRepository;
import com.GraduationProject.test.repositories.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {
        AppUser appUser = new AppUser(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getPhoneNumber(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );
        userRepository.save(appUser);
        return appUser.getName();
    }
    @Override
    public LoginMesage  loginUser(LoginDTO loginDTO) {
        String msg = "";
        AppUser user1 = UserRepository.findByName(loginDTO.getName());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<AppUser> user = UserRepository.findOneByNameAndPassword(loginDTO.getName(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginMesage("Login Success", true);
                } else {
                    return new LoginMesage("Login Failed", false);
                }
            } else {
                return new LoginMesage("password Not Match", false);
            }
        }else {
            return new LoginMesage("Email not exits", false);
        }
    }
}
