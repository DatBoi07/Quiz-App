package com.assignment.quiz_app.service;

import com.assignment.quiz_app.entity.Question;

import java.util.*;

public class QuizSession {
    private final UUID sessionId;
    private final Map<Integer, Boolean> answeredQuestions = new HashMap<>();
    private final Map<Integer, Question> questionHistory = new HashMap<>();

    public QuizSession(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void addQuestion(Question question) {
        questionHistory.put(question.getId(), question);
    }

    public Question getQuestion(int questionId) {
        return questionHistory.get(questionId);
    }

    public void recordAnswer(int questionId, boolean isCorrect) {
        answeredQuestions.put(questionId, isCorrect);
    }

    public QuizResult getResult() {
        long correct = answeredQuestions.values().stream().filter(Boolean::booleanValue).count();
        long incorrect = answeredQuestions.size() - correct;
        return new QuizResult(answeredQuestions.size(), correct, incorrect);
    }
}