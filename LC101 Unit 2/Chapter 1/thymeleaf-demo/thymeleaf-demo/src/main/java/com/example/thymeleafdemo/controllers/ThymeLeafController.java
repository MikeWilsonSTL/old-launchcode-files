package com.example.thymeleafdemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Controller
public class ThymeLeafController {
    @GetMapping
    public String home(Model model){
        model.addAttribute("bruh", "we out here bruh");
        return "home";
    }

    @GetMapping("quote")
    public String getQuote(Model model) {
        String returnedQuote;
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.quotable.io/random?minLength=100"))
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
//            System.out.println("API Call confirmation:\n");
//            System.out.println("Status code: " + response.statusCode());
//            System.out.println("Headers: " + response.headers().allValues("content-type"));
//            System.out.println("Body: " + response.body() + "\n");
//
//            System.out.println("Quote of the Day:\n");
            String quote = response.body();

            int contentBeginning = quote.indexOf("content") + 9;
            int authorBeginning = quote.indexOf("author") + 9;
            int contentEnd = authorBeginning - 11;
            int authorEnd = quote.indexOf("tags") - 3;

            String content = quote.substring(contentBeginning, contentEnd);
            String author = quote.substring(authorBeginning, authorEnd);

            returnedQuote = content + " - " + author;
            model.addAttribute("quote", returnedQuote);

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            model.addAttribute("quote", "ERROR: Could not access API. Try waiting one minute and try again.");
        }
        return "home";
    }
}
