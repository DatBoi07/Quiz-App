package com.assignment.quiz_app.service;

import java.util.*;
public class AnswerSubmission {
    private UUID sessionId;
    private int questionId;
    private int selectedOptionIndex;

    public UUID getSessionId() {
        return sessionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getSelectedOptionIndex() {
        return selectedOptionIndex;
    }
}
