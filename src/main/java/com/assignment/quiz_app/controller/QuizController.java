package com.assignment.quiz_app.controller;

import com.assignment.quiz_app.entity.Question;
import com.assignment.quiz_app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("/start")
	public QuizSession startQuiz() {
		return quizService.startNewSession();
	}

	@GetMapping("/question")
	public Question getQuestion(@RequestParam UUID sessionId) {
		return quizService.getRandomQuestion(sessionId);
	}

	@PostMapping("/submit")
	public SubmissionResult submitAnswer(@RequestBody AnswerSubmission submission) {
		return quizService.submitAnswer(submission);
	}

	@GetMapping("/results")
	public QuizResult getResults(@RequestParam UUID sessionId) {
		return quizService.getQuizResult(sessionId);
	}
}
