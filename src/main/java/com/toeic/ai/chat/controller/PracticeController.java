
package com.toeic.ai.chat.controller;

import com.toeic.ai.chat.model.Question;
import com.toeic.ai.chat.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/practice")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @GetMapping("/random")
    public Question getRandomQuestion() {
        return practiceService.getRandomQuestion();
    }

    @GetMapping("/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return practiceService.getQuestionsByCategory(category);
    }
}
