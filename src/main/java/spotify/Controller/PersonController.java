package spotify.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import spotify.Dto.AuthenticationRequest;
import spotify.Dto.RegisterRequest;
import spotify.Service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService service;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return service.Register(registerRequest);
    }
    @GetMapping("/check-session")
    public String checkSession(HttpSession session) {
        String token = (String) session.getAttribute("authToken");
        if (token == null) {
            return "No token in session. Please login.";
        }

        boolean valid = service.validateToken(token);
        return valid ? "Token is valid." : "Invalid token.";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequest authenticationRequest , HttpSession session) {
        return service.login(authenticationRequest , session);
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout successful";
    }

    @GetMapping
    public List<RegisterRequest> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RegisterRequest getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public String updatedPerson(@PathVariable Long id, RegisterRequest updatePerson) {
        service.updatePerson(id, updatePerson);
        return "Success updated";
    }

    @DeleteMapping(value = "/{id}")
    public void deletaPerson(@PathVariable Long id) {
        service.deletePerson(id);
    }
    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
}
