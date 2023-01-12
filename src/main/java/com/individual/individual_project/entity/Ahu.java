package com.individual.individual_project.entity;

import com.individual.individual_project.dto.AhuRequestDto;
import com.individual.individual_project.dto.LoginRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String comment;

    @OneToMany(mappedBy = "ahu")
    private List<Comment> comments = new ArrayList<>();


    public Ahu(AhuRequestDto requestDto) {
        this.head = requestDto.getHead();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public Ahu(LoginRequestDto loginRequestDto){
        this.username = getUsername();
    }

    public void update(AhuRequestDto requestDto) {
        this.head = requestDto.getHead();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public void createComment(AhuRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
