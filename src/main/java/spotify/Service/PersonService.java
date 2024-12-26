package book.Service;

import book.Dto.AuthenticationRequest;
import book.Dto.RegisterRequest;
import book.Entity.Person;
import book.Entity.Role_Person;
import book.Mapper.PersonMapper;
import book.Repository.PersonRepository;
import book.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;
    private final PersonMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public String Login(AuthenticationRequest request) {
        Person person = repository.findByEmail(request.getEmail());
        if (person == null) {
            return "User not found";
        }
        if (!passwordEncoder.matches(request.getPassword(), person.getPassword())) {
            return "Invalid email or password";
        }
        return "Login Success";
    }


    public String Register(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            return "User already exists";
        }
        Role_Person defaultRole = roleRepository.findById(2L).orElse(null);

        Person newPerson = mapper.toModel(request);
        newPerson.setPassword(passwordEncoder.encode(newPerson.getPassword()));
        newPerson.setRole(defaultRole);
        newPerson.setPhoneNumber(request.getPhone());
        repository.save(newPerson);
        return "Register Success";
    }

    public List<RegisterRequest> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    public RegisterRequest getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    public String updatePerson(Long id, RegisterRequest updatedRequest) {
        Person person = repository.findById(id).orElse(null);
        if (person == null) {
            return "User not found";
        }
        person.setName(updatedRequest.getName());
        person.setEmail(updatedRequest.getEmail());
        if (updatedRequest.getPassword() != null && !updatedRequest.getPassword().isEmpty()) {
            person.setPassword(passwordEncoder.encode(updatedRequest.getPassword()));
        }
        person.setPhoneNumber(updatedRequest.getPhone());
        person.setAddress(updatedRequest.getAddress());

        repository.save(person);
        return "Update Success";
    }

    public boolean deletePerson(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
