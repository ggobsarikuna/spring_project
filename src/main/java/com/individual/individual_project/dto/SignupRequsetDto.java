package com.individual.individual_project.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Data
public class SignupRequsetDto {

    @Length(min = 4, max = 10, message = "4자 이상 10자 이하 가능")
    @Pattern(regexp = "^[a-z]+[a-z0-9]{4,10}$", message = "알파벳 소문자, 숫자로 구성되어야 합니다.")
    private String username;

    @Length(min = 8, max = 15, message = "8자 이상 15자 이하 가능")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d~!@#$%^&*()+|=]{8,15}$", message = "알파벳 대소문자, 숫자로 구성되어야 합니다.")
    private String password;
}
