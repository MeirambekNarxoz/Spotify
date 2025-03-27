package spotify.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import spotify.Dto.AuthenticationRequest;
import spotify.Dto.PersonDto;
import spotify.Dto.RegisterRequest;
import spotify.Entity.Person;
import spotify.Entity.Role_Person;
import spotify.Mapper.PersonListMapper;
import spotify.Mapper.PersonMapper;
import spotify.Repository.PersonRepository;
import spotify.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;
    private final PersonMapper mapper;
    private final PersonListMapper listMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Value("${jwt.secret-key}")
    private String secretKeyString;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = new SecretKeySpec(
                secretKeyString.getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS256.getJcaName()
        );
    }
//    Login
    public String login(AuthenticationRequest request) {
        Person person = repository.findByEmail(request.getEmail());
        if (person == null) {
            throw new UsernameNotFoundException("User with email " + request.getEmail() + " not found");
        }
        if (!passwordEncoder.matches(request.getPassword(), person.getPassword())) {
            throw new BadCredentialsException("Invalid password for email " + request.getEmail());
        }

        UserDetails userDetails = User.builder()
                .username(person.getEmail())
                .password(person.getPassword())
                .authorities(person.getRole().getRole())
                .build();

        return generateToken(userDetails);
    }
// Generate Token
    private String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
//    Register
    public String Register(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("User already exists");
        }
        Role_Person defaultRole = roleRepository.findById(2L)
                .orElseThrow(() -> new IllegalArgumentException("Default role not found"));

        Person newPerson = mapper.toModel(request);
        newPerson.setPassword(passwordEncoder.encode(newPerson.getPassword()));
        newPerson.setRole(defaultRole);
        newPerson.setPhoneNumber(request.getPhone());
        repository.save(newPerson);

        UserDetails userDetails = User.builder()
                .username(newPerson.getEmail())
                .password(newPerson.getPassword())
                .authorities(newPerson.getRole().getRole())
                .build();

        return generateToken(userDetails);
    }

    public List<PersonDto> getAll() {
        return listMapper.toDtoList(repository.findAll());
    }

    public RegisterRequest getById(Long id)
    {
        return mapper.toDto(repository.findById(id).orElse(null));
    }
//    Update User
    public String updatePerson(Long id, RegisterRequest updatedRequest) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (updatedRequest.getName() != null) {
            person.setName(updatedRequest.getName());
        }
        if (updatedRequest.getEmail() != null) {
            person.setEmail(updatedRequest.getEmail());
        }
        if (updatedRequest.getPassword() != null && !updatedRequest.getPassword().isEmpty()) {
            person.setPassword(passwordEncoder.encode(updatedRequest.getPassword()));
        }
        if (updatedRequest.getPhone() != null) {
            person.setPhoneNumber(updatedRequest.getPhone());
        }
        if (updatedRequest.getAddress() != null) {
            person.setAddress(updatedRequest.getAddress());
        }
        repository.save(person);
        return "Update Success";
    }
//    Update User Role
    public String updateRole(Long id, PersonDto dto) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (dto.getRole() != null && !dto.getRole().isEmpty()) {
            Role_Person newRole = roleRepository.findByRole(dto.getRole())
                    .orElseThrow(() -> new IllegalArgumentException("Role not found: " + dto.getRole()));
            person.setRole(newRole);
        }
        repository.save(person);
        return "Role updated successfully";
    }
//    Delete User by id
    public void deletePerson(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        repository.deleteById(id);
    }

}