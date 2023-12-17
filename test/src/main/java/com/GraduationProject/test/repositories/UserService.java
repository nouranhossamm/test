package com.GraduationProject.test.repositories;

import com.GraduationProject.test.DTOs.LoginDTO;
import com.GraduationProject.test.DTOs.LoginMesage;
import com.GraduationProject.test.DTOs.UserDTO;

public interface UserService {
    String addUser(UserDTO userDTO);
    LoginMesage loginUser(LoginDTO loginDTO);
}

