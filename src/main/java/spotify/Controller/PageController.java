package spotify.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/person/login")
    public String loginPage() {
        return "Person/login";
    }

    @GetMapping("person/register")
    public String registerPage() {
        return "Person/register";
    }

    @GetMapping("/person/mainPage")
    public String MainPage() {
        return "mainPage";
    }

    @GetMapping("/person/AdminPage")
    public String AdminPage() {
        return "admin";
    }

    @GetMapping("/person/updatePerson")
    public String UpdatePersonPage() {
        return "Person/updatePerson";
    }
}
