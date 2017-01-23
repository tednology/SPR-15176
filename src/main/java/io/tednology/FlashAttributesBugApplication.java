package io.tednology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;

@Controller
@SpringBootApplication
public class FlashAttributesBugApplication {

    @GetMapping("/bad")
    ResponseEntity<Void> bad(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Bad!");
        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create("/"))
            .build();
    }

    @GetMapping("/good")
    String good(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Good!");
        return "redirect:/";
    }

    @GetMapping("/")
    String home(Model model) {
        model.asMap().entrySet().forEach(System.err::println);
        return "home";
    }

    public static void main(String[] args) {
        SpringApplication.run(FlashAttributesBugApplication.class, args);
    }
}
