package com.individual.individual_project.entity;

import com.individual.individual_project.dto.AhuRequestDto;
import com.individual.individual_project.dto.AhuRequestDto2;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Ahu extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String head;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String password;


    public Ahu(AhuRequestDto requestDto) {
        this.head = requestDto.getHead();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void update(AhuRequestDto requestDto) {
        this.head = requestDto.getHead();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
