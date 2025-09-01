
package com.toeic.ai.chat.controller;

import com.toeic.ai.chat.model.Result;
import com.toeic.ai.chat.model.User;
import com.toeic.ai.chat.repository.ResultRepository;
import com.toeic.ai.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserResults(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Result> results = resultRepository.findByUser(user);
        return ResponseEntity.ok(results);
    }
}
