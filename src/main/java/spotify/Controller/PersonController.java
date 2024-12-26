package spotify.Controller;

import spotify.Dto.AuthenticationRequest;
import spotify.Dto.BookDto;
import spotify.Dto.RegisterRequest;
import spotify.Service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService service;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return service.Register(registerRequest);
    }
    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequest authenticationRequest) {
        return service.Login(authenticationRequest);
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
//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
}
