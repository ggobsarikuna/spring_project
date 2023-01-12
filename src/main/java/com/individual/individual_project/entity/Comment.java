package com.individual.individual_project.entity;

import com.individual.individual_project.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String comment;
    @ManyToOne
    @JoinColumn(name = "AHU_ID", nullable = true)
    private Ahu ahu;

    public Comment(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }
}
