package com.assignment.quiz_app.repository;

import com.assignment.quiz_app.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}