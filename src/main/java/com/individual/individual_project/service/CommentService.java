package com.individual.individual_project.service;

import com.individual.individual_project.dto.AhuRequestDto;
import com.individual.individual_project.dto.AhuRequestDto2;
import com.individual.individual_project.dto.CommentRequestDto;
import com.individual.individual_project.entity.Ahu;
import com.individual.individual_project.entity.Comment;
import com.individual.individual_project.jwt.JwtUtil;
import com.individual.individual_project.repository.AhuRepository;
import com.individual.individual_project.repository.CommentRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    public String createComment(Long id, AhuRequestDto requestDto, AhuRepository ahuRepository, CommentRequestDto commentDto, HttpServletRequest request) {
        Comment comment = new Comment(commentDto);
        Ahu ahu = new Ahu(requestDto);
        boolean exists = ahuRepository.existsById(id);
        if (tokenTest(request)){
            if(exists){
                ahu.createComment(requestDto);
            }
        } else {
            return ("Token Error");
        }
        return comment.getComment();
    }

    private boolean tokenTest(HttpServletRequest request){
        String token = jwtUtil.resolveToken(request);

        if (token != null){
            if (jwtUtil.validateToken(token)){
                return true;
            } else {
                return false;
            }
        }

        return false;
    }
}
