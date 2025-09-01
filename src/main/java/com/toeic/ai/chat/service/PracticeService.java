
package com.toeic.ai.chat.service;

import com.toeic.ai.chat.model.Question;
import com.toeic.ai.chat.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PracticeService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question getRandomQuestion() {
        return questionRepository.findRandomQuestion();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }
}
