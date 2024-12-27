//package spotify.Service;
//
//import spotify.Dto.AuthenticationRequest;
//import spotify.Dto.RegisterRequest;
//import spotify.Entity.Person;
//import spotify.Entity.Role_Person;
//import spotify.Mapper.PersonMapper;
//import spotify.Repository.PersonRepository;
//import spotify.Repository.RoleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PersonService {
//    private final PersonRepository repository;
//    private final PersonMapper mapper;
////    private final PasswordEncoder passwordEncoder;
//    private final RoleRepository roleRepository;
//
//    /**
//     * Login method to authenticate a user by email and password.
//     *
//     * @param request contains email and password
//     * @return success message or exception if login fails
//     */
//    public String Login(AuthenticationRequest request) {
//        Person person = repository.findByEmail(request.getEmail());
//        if (person == null) {
//            throw new IllegalArgumentException("User not found");
//        }
//        if (!passwordEncoder.matches(request.getPassword(), person.getPassword())) {
//            throw new IllegalArgumentException("Invalid email or password");
//        }
//        return "Login Success";
//    }
//
//    /**
//     * Register a new user.
//     *
//     * @param request contains user details
//     * @return success message if registration is successful
//     */
//    public String Register(RegisterRequest request) {
//        if (repository.existsByEmail(request.getEmail())) {
//            throw new IllegalArgumentException("User already exists");
//        }
//        Role_Person defaultRole = roleRepository.findById(2L)
//                .orElseThrow(() -> new IllegalArgumentException("Default role not found"));
//
//        Person newPerson = mapper.toModel(request);
//        newPerson.setPassword(passwordEncoder.encode(newPerson.getPassword()));
//        newPerson.setRole(defaultRole);
//        newPerson.setPhoneNumber(request.getPhone());
//        repository.save(newPerson);
//        return "Register Success";
//    }
//
//    /**
//     * Get all users.
//     *
//     * @return list of user DTOs
//     */
//    public List<RegisterRequest> getAll() {
//        return mapper.toDtoList(repository.findAll());
//    }
//
//    /**
//     * Get user by ID.
//     *
//     * @param id user ID
//     * @return user DTO or null if not found
//     */
//    public RegisterRequest getById(Long id) {
//        return mapper.toDto(repository.findById(id).orElse(null));
//    }
//
//    /**
//     * Update user details.
//     *
//     * @param id user ID
//     * @param updatedRequest contains updated user details
//     * @return success message or exception if user not found
//     */
//    public String updatePerson(Long id, RegisterRequest updatedRequest) {
//        Person person = repository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//
//        if (updatedRequest.getName() != null) {
//            person.setName(updatedRequest.getName());
//        }
//        if (updatedRequest.getEmail() != null) {
//            person.setEmail(updatedRequest.getEmail());
//        }
//        if (updatedRequest.getPassword() != null && !updatedRequest.getPassword().isEmpty()) {
//            person.setPassword(passwordEncoder.encode(updatedRequest.getPassword()));
//        }
//        if (updatedRequest.getPhone() != null) {
//            person.setPhoneNumber(updatedRequest.getPhone());
//        }
//        if (updatedRequest.getAddress() != null) {
//            person.setAddress(updatedRequest.getAddress());
//        }
//
//        repository.save(person);
//        return "Update Success";
//    }
//
//    /**
//     * Delete a user by ID.
//     *
//     * @param id user ID
//     */
//    public void deletePerson(Long id) {
//        if (!repository.existsById(id)) {
//            throw new IllegalArgumentException("User not found");
//        }
//        repository.deleteById(id);
//    }
//}
