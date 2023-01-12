package com.individual.individual_project.dto;

import com.individual.individual_project.entity.Comment;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class CommentRequestDto {
    private Long id;
    private String comment;

    public CommentRequestDto(Long id, Comment comment){
        this.id = id;
        this.comment = comment.getComment();
    }
}
