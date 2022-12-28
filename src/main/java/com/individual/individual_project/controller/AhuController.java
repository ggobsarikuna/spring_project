package com.individual.individual_project.controller;

import com.individual.individual_project.dto.AhuRequestDto;
import com.individual.individual_project.dto.AhuRequestDto2;
import com.individual.individual_project.entity.Ahu;
import com.individual.individual_project.service.AhuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AhuController {
    private final AhuService ahuService;

    @PostMapping("/api/ahu")
    public Ahu createAhu(@RequestBody AhuRequestDto requestDto){
        return ahuService.createAhu(requestDto);
    }

    @GetMapping("/api/ahu")
    public List<AhuRequestDto2> getAhu(){
        return ahuService.getAhu();
    }

    @PutMapping("/api/ahu/{id}")
    public String updateAhu(@PathVariable Long id, @RequestBody AhuRequestDto requestDto){
        return ahuService.update(id, requestDto);
    }

    @DeleteMapping("/api/ahu/{id}")
    public String deleteAhu(@PathVariable Long id, @RequestBody AhuRequestDto requestDto){
        return ahuService.deleteAhu(id, requestDto);
    }

    @GetMapping("/api/ahu/{id}")
    public List<AhuRequestDto2> getAhu_id(@PathVariable Long id){
        return ahuService.getAhu_id(id);
    }

}
