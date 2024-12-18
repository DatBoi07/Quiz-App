package com.assignment.quiz_app.service;

public class SubmissionResult {
    private final boolean answer;

    public SubmissionResult(boolean correct) {
        this.answer= correct;
    }

    public boolean isCorrect() {
        return answer;
    }
}
