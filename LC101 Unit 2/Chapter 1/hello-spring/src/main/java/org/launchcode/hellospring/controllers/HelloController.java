package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("goodbye") // Lives at /hello/goodbye
    @ResponseBody
    public String goodbye(){
        return "Goodbye, Spring!";
    }

    // Handles request of the form /hello?name=LaunchCode
    @RequestMapping(value = "hello", method = {RequestMethod.POST, RequestMethod.GET})
    public String helloWithQueryParam(@RequestParam String name, Model model){
        String greeting = "Hello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "hello";
    }

    // Handles requests of the form /hello/LaunchCode
    @GetMapping("{name}")
    public String helloWithPathParam(@PathVariable String name, Model model){
        String greeting = "Hello, " + name + "!";
        model.addAttribute("greeting", greeting);
        return "hello";
    }

    @GetMapping("form")
    public String helloForm() {
        return "form";
    }

    @GetMapping("quote")
    public String randomQuote(Model model){
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

            String quote = response.body();

            int contentBeginning = quote.indexOf("content") + 9;
            int authorBeginning = quote.indexOf("author") + 9;
            int contentEnd = authorBeginning - 11;
            int authorEnd = quote.indexOf("tags") - 3;

            if(authorBeginning < contentBeginning){ //If the "author" tag comes before the "content" tag
                contentBeginning = quote.indexOf("content") + 9;
                authorBeginning = quote.indexOf("author") + 9;
                contentEnd = quote.indexOf("tags") - 2;
                authorEnd = quote.indexOf("content") - 3;

                String content = quote.substring(contentBeginning, contentEnd);
                String author = quote.substring(authorBeginning, authorEnd);

                String returnedQuote = content + " - " + author;
                model.addAttribute("quote", returnedQuote);
                return "quote";
            }

            String content = quote.substring(contentBeginning, contentEnd);
            String author = quote.substring(authorBeginning, authorEnd);

            String returnedQuote = content + " - " + author;
            model.addAttribute("quote", returnedQuote);
            return "quote";

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        String returnedQuote = "ERROR: API Call not working :(";
        model.addAttribute("quote", returnedQuote);
        return "quote";
    }

    @GetMapping("hello-names")
    public String helloNames(Model model){
        List<String> names = new ArrayList<>();
        names.add("We");
        names.add("Out");
        names.add("Here");
        model.addAttribute("names", names);
        return "hello-list";
    }
}
