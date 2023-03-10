package com.individual.individual_project.service;

import com.individual.individual_project.dto.LoginRequestDto;
import com.individual.individual_project.dto.SignupRequsetDto;
import com.individual.individual_project.entity.User;
import com.individual.individual_project.entity.UserRoleEnum;
import com.individual.individual_project.jwt.JwtUtil;
import com.individual.individual_project.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtill;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    public void signup(SignupRequsetDto signupRequsetDto) { //중복 조회 및 회원 저장
        String username = signupRequsetDto.getUsername();
        String password = signupRequsetDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);

        if(found.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if(signupRequsetDto.getAdmin().equals(UserRoleEnum.ADMIN)){
            if(!signupRequsetDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);
    }
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtill.createToken(user.getUsername(), user.getRole()));
    }
}
