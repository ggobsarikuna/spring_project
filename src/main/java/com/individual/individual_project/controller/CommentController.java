package com.individual.individual_project.controller;

import com.individual.individual_project.dto.AhuRequestDto;
import com.individual.individual_project.dto.AhuRequestDto2;
import com.individual.individual_project.dto.CommentRequestDto;
import com.individual.individual_project.repository.AhuRepository;
import com.individual.individual_project.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login/ahu")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{id}/comment")
    public String creatComment(@PathVariable Long id, @RequestBody AhuRequestDto requestDto, AhuRepository ahuRepository, CommentRequestDto commentDto, HttpServletRequest request){
        return commentService.createComment(id, requestDto, ahuRepository, commentDto, request);
    }
}
