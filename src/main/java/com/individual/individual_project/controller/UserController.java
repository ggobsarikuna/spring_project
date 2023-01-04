package com.individual.individual_project.controller;

import com.individual.individual_project.dto.LoginRequestDto;
import com.individual.individual_project.dto.SignupRequsetDto;
import com.individual.individual_project.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid SignupRequsetDto signupRequsetDto, BindingResult errors){
        if (errors.hasErrors()){
            return errors.getFieldError().toString();
        }
        userService.signup(signupRequsetDto);
        return "회원가입 성공!";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        userService.login(loginRequestDto, response);
        return "로그인 성공!";
    }
}
