package com.individual.individual_project.repository;

import com.individual.individual_project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>{
}
