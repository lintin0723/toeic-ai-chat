
package com.toeic.ai.chat.service;

import com.toeic.ai.chat.model.Question;
import com.toeic.ai.chat.model.Result;
import com.toeic.ai.chat.model.User;
import com.toeic.ai.chat.repository.QuestionRepository;
import com.toeic.ai.chat.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedbackService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Result checkAnswer(User user, Long questionId, String userAnswer) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(userAnswer);
        int score = isCorrect ? 10 : 0; // Simple scoring, can be adjusted

        Result result = new Result();
        result.setUser(user);
        result.setQuestion(question);
        result.setUserAnswer(userAnswer);
        result.setCorrect(isCorrect);
        result.setScore(score);
        result.setCreatedAt(LocalDateTime.now());

        return resultRepository.save(result);
    }
}
