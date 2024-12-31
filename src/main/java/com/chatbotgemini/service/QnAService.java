package com.chatbotgemini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class QnAService {
    //Access to APIKey and URL [Gemini]
     @Value("${gemini.api.url}")
     private  String geminiApiUrl;

    @Value("${gemini.api.key}")
    private  String geminiApiKey;

    @Autowired
    private WebClient webClient;



    public String getAnswer(String question){
   //Construct the payload request
        Map<String,Object> requestBody=Map.of(
                "contents", new Object[]{
                        Map.of("parts", new Object[]{
                                Map.of("text", question)
                        })
                });

        //Make API Call
     String response= webClient.post()
                .uri(geminiApiUrl+geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
   //Return response
    return response;
}
}