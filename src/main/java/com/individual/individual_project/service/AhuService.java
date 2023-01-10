package com.individual.individual_project.service;

import com.individual.individual_project.dto.AhuRequestDto2;
import com.individual.individual_project.entity.Ahu;
import com.individual.individual_project.dto.AhuRequestDto;
import com.individual.individual_project.entity.User;
import com.individual.individual_project.entity.UserRoleEnum;
import com.individual.individual_project.jwt.JwtUtil;
import com.individual.individual_project.repository.AhuRepository;
import com.individual.individual_project.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AhuService {
    private final AhuRepository ahuRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtill;

    public String createAhu(AhuRequestDto requestDto, HttpServletRequest request){ //작성
        String token = jwtUtill.resolveToken(request);

        Ahu ahu = new Ahu(requestDto);
        if (token != null){
            if (jwtUtill.validateToken(token)){
                ahuRepository.save(ahu);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
        }
        return ahu.getContents();
    }

    public List<AhuRequestDto2> getAhu() { //조회
        List<Ahu> a = ahuRepository.findAllByOrderByModifiedAtDesc();
        List<AhuRequestDto2> b = new ArrayList<>();
        for (Ahu ahus : a){
            b.add(new AhuRequestDto2(ahus.getId(), ahus));
        }
        return b;
    }

    public List<AhuRequestDto2> getAhu_id(Long id) { //조회
        List<Ahu> a = ahuRepository.findAllByOrderByModifiedAtDesc();
        List<AhuRequestDto2> b = new ArrayList<>();
        for (Ahu ahus : a){
            if (ahus.getId().equals(id)){
                b.add(new AhuRequestDto2(ahus.getId(), ahus));
            }
        }
        return b;
    }

    public String update(Long id, AhuRequestDto requestDto, HttpServletRequest request, User user){ //수정
        String token = jwtUtill.resolveToken(request);
        Ahu ahu1 = ahuRepository.findById(id).orElseThrow();
        Claims claims;
        Ahu ahu = new Ahu(requestDto);
        UserRoleEnum userRoleEnum = user.getRole();

        if(userRoleEnum == UserRoleEnum.ADMIN){
            ahu1.update(requestDto);
            return ahu.getContents();
        }

        if (token != null) {
            if (jwtUtill.validateToken(token)) {
                claims = jwtUtill.getUserInfoFromToken(token);
                if (claims.getSubject().equals(ahu1.getUsername())) {
                    ahu1.update(requestDto);
                } else {
                    return "Error";
                }
            } else {
                throw new IllegalArgumentException("Token Error");
            }
        }

        return ahu.getContents();
    }

    public String deleteAhu(Long id, AhuRequestDto requestDto, HttpServletRequest request, User user){ //삭제
        String token = jwtUtill.resolveToken(request);
        Ahu ahu1 = ahuRepository.findById(id).orElseThrow();
        Claims claims;

        UserRoleEnum userRoleEnum = user.getRole();

        if(userRoleEnum == UserRoleEnum.ADMIN){
            ahuRepository.deleteById(id);
            return "삭제 완료!";
        }

        if (token != null){
            if (jwtUtill.validateToken(token)){
                claims = jwtUtill.getUserInfoFromToken(token);
                if(claims.getSubject().equals(ahu1.getUsername())){
                    ahuRepository.deleteById(id);
                }else {
                    return "Error";
                }
            } else {
                throw new IllegalArgumentException("Token Error");
            }
        }
        return "삭제완료";
    }
}
