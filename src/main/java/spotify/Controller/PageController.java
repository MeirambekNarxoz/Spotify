package spotify.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/person/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("person/register")
    public String registerPage() {
        return "register";
    }
    @GetMapping("/person/mainPage")
    public String MainPage() {
        return "mainPage";
    }
}
