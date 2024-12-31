package com.chatbotgemini.controller;

import com.chatbotgemini.service.QnAService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AIWebController {

    private final QnAService qnAService;

    @PostMapping("/ask")
    public ResponseEntity<?> askQuetion(@RequestBody Map<String,String> payload){
        String question= payload.get("question");
        String answer= qnAService.getAnswer(question);
        return ResponseEntity.ok(answer);
    }
}
