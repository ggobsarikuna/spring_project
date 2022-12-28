package com.individual.individual_project.service;

import com.individual.individual_project.dto.AhuRequestDto2;
import com.individual.individual_project.entity.Ahu;
import com.individual.individual_project.dto.AhuRequestDto;
import com.individual.individual_project.repository.AhuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AhuService {
    private final AhuRepository ahuRepository;

    @Transactional
    public Ahu createAhu(AhuRequestDto requestDto){ //작성
        Ahu ahu = new Ahu(requestDto);
        ahuRepository.save(ahu);
        return ahu;
    }

    @Transactional
    public List<AhuRequestDto2> getAhu() { //조회
        List<Ahu> a = ahuRepository.findAllByOrderByModifiedAtDesc();
        List<AhuRequestDto2> b = new ArrayList<>();
        for (Ahu ahus : a){
            b.add(new AhuRequestDto2(ahus.getId(), ahus));
        }
        return b;
    }

    @Transactional
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

    @Transactional
    public String update(Long id, AhuRequestDto requestDto){ //수정
        Ahu ahu = ahuRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (!ahu.getPassword().equals(requestDto.getPassword())){
            return "비밀번호가 다릅니다";
        }
        ahu.update(requestDto);
        return "수정완료";
    }

    @Transactional
    public String deleteAhu(Long id, AhuRequestDto requestDto){ //삭제
        Ahu ahu = ahuRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        ahuRepository.deleteById(id);
        if (!ahu.getPassword().equals(requestDto.getPassword())){
            return "비밀번호가 다릅니다";
        }
        return "삭제완료";
    }
}
