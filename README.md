# Spring MVC Flash Attribute Bug Demo

In short, when Flash scoped attributes are added in a @Controller method and then a redirect is executed by returning a type of `ResponseBody` with an HTTP status of 302 Found, the Flash scoped attributes are lost.

```Java
@Controller
public class DemoController {
    
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
        // Will print flash scoped message "Good!", but not "Bad!"
        model.asMap().entrySet().forEach(System.err::println);
        return "home";
    }
}
```