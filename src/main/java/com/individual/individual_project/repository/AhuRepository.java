package com.individual.individual_project.repository;

import com.individual.individual_project.entity.Ahu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AhuRepository extends JpaRepository<Ahu, Long> {
    List<Ahu> findAllByOrderByModifiedAtDesc();
}
