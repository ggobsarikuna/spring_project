package com.individual.individual_project.dto;

import com.individual.individual_project.entity.Ahu;
import com.individual.individual_project.entity.Timestamped;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class AhuRequestDto2 extends Timestamped {
    private String head;
    private String username;
    private String contents;
    private Long id;

    private LocalDateTime createAt;

    private LocalDateTime modifiedAt;
    private String comment;


    public AhuRequestDto2(Long id, Ahu ahu){
        this.id = id;
        this.head = ahu.getHead();
        this.username = ahu.getUsername();
        this.contents = ahu.getContents();
        this.createAt = ahu.getCreateAt();
        this.modifiedAt = ahu.getModifiedAt();
        this.comment = ahu.getComment();
    }
}
