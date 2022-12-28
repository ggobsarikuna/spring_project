package com.individual.individual_project.dto;

import com.individual.individual_project.entity.Timestamped;
import lombok.Getter;

@Getter
public class AhuRequestDto extends Timestamped {
    private String head;
    private String username;
    private String contents;
    private String password;


}
