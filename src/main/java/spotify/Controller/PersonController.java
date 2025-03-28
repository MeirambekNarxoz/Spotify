package spotify.Controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import spotify.Dto.AuthenticationRequest;
import spotify.Dto.PersonDto;
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
//    Register
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        return service.Register(registerRequest);
    }
//    Login
    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequest authenticationRequest ) {
        return service.login(authenticationRequest );
    }
//    Logout
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logout successful";
    }
//    Get All User
    @GetMapping
    public List<PersonDto> getAll() {
        return service.getAll();
    }
//    Get User by id
    @GetMapping("/{id}")
    public RegisterRequest getById(@PathVariable Long id) {
        return service.getById(id);
    }
//    Update User
    @PutMapping("/{id}")
    public String updatedPerson(@PathVariable Long id, RegisterRequest updatePerson) {
        service.updatePerson(id, updatePerson);
        return "Success updated";
    }
//    Update User Role
    @PutMapping("/role/{id}")
    public String updatedRole(@PathVariable Long id, @RequestBody PersonDto dto) {
        service.updateRole(id, dto);
        return "Success updated";
    }
//    Delete User by id
    @DeleteMapping(value = "/{id}")
    public void deletaPerson(@PathVariable Long id) {
        service.deletePerson(id);
    }
    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
}
