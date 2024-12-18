package com.assignment.quiz_app.service;

import com.assignment.quiz_app.entity.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assignment.quiz_app.repository.QuestionRepository;



import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class QuizService {

    private final Map<UUID, QuizSession> sessions = new ConcurrentHashMap<>();
    private final List<Question> questionPool = new ArrayList<>();
    @Autowired
    private QuestionRepository questionRepository;

    @PostConstruct
    public void seedDatabase() {
        List<Question> questions = Arrays.asList(
                new Question(0, "What is the capital of France?", Arrays.asList("Paris", "London", "Berlin", "Madrid"), 0),
                new Question(0, "What is 2 + 2?", Arrays.asList("3", "4", "5", "6"), 1),
                new Question(0, "Which programming language is this?", Arrays.asList("Java", "Python", "C++", "Ruby"), 0)
        );
        questionRepository.saveAll(questions);
    }


//    @PostConstruct
//    private void seedData() {
//        questionPool.add(new Question(1, "What is the capital of France?", Arrays.asList("Paris", "London", "Berlin", "Madrid"), 0));
//        questionPool.add(new Question(2, "What is 2 + 2?", Arrays.asList("3", "4", "5", "6"), 1));
//        questionPool.add(new Question(3, "What is the largest planet in our Solar System?", Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"), 2));
//    }
//

    public QuizSession startNewSession() {
        UUID sessionId = UUID.randomUUID();
        QuizSession session = new QuizSession(sessionId);
        sessions.put(sessionId, session);
        return session;
    }

    public Question getRandomQuestion(UUID sessionId) {
        if (!sessions.containsKey(sessionId)) {
            throw new IllegalArgumentException("Invalid session ID");
        }
        QuizSession session = sessions.get(sessionId);
        List<Question> questionPool = questionRepository.findAll();
        if (questionPool.isEmpty()) {
            throw new IllegalStateException("No questions available in the database");
        }

        // Select a random question
        Random random = new Random();
        Question question = questionPool.get(random.nextInt(questionPool.size()));

        session.addQuestion(question);
        return question;
    }

    public SubmissionResult submitAnswer(AnswerSubmission submission) {
        QuizSession session = sessions.get(submission.getSessionId());
        if (session == null) {
            throw new IllegalArgumentException("Invalid session ID");
        }
        Question question = session.getQuestion(submission.getQuestionId());
        boolean isCorrect = question.getCorrectOptionIndex() == submission.getSelectedOptionIndex();
        session.recordAnswer(submission.getQuestionId(), isCorrect);
        return new SubmissionResult(isCorrect);
    }

    public QuizResult getQuizResult(UUID sessionId) {
        QuizSession session = sessions.get(sessionId);
        if (session == null) {
            throw new IllegalArgumentException("Invalid session ID");
        }
        return session.getResult();
    }
}