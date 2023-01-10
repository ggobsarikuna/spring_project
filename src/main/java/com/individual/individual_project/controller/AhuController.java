package com.individual.individual_project.controller;

import com.individual.individual_project.dto.AhuRequestDto;
import com.individual.individual_project.dto.AhuRequestDto2;
import com.individual.individual_project.entity.Ahu;
import com.individual.individual_project.service.AhuService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.individual.individual_project.security.UserDetailsImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class AhuController {
    private final AhuService ahuService;

    @PostMapping("/ahu") //작성
    public String createAhu(@RequestBody AhuRequestDto requestDto, HttpServletRequest request){
        return ahuService.createAhu(requestDto, request);
    }

    @GetMapping("/ahu") //전체 조회
    public List<AhuRequestDto2> getAhu(){
        return ahuService.getAhu();
    }

    @PutMapping("/ahu/{id}") // 선택 수정
    public String updateAhu(@PathVariable Long id, @RequestBody AhuRequestDto requestDto, HttpServletRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ahuService.update(id, requestDto, request, userDetails.getUser());
    }

    @DeleteMapping("/ahu/{id}") // 선택 삭제
    public String deleteAhu(@PathVariable Long id, @RequestBody AhuRequestDto requestDto, HttpServletRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ahuService.deleteAhu(id, requestDto, request, userDetails.getUser());
    }

    @GetMapping("/ahu/{id}") // 선택 조회
    public List<AhuRequestDto2> getAhu_id(@PathVariable Long id){
        return ahuService.getAhu_id(id);
    }

}
