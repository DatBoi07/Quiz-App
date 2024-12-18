package com.assignment.quiz_app.service;

public class QuizResult {
    private final long totalQuestions;
    private final long correctAnswers;
    private final long incorrectAnswers;

    public QuizResult(long totalQuestions, long correctAnswers, long incorrectAnswers) {
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
    }

    public long getTotalQuestions() {
        return totalQuestions;
    }

    public long getCorrectAnswers() {
        return correctAnswers;
    }

    public long getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
