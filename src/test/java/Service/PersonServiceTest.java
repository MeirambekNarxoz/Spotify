package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import spotify.Dto.AuthenticationRequest;
import spotify.Dto.PersonDto;
import spotify.Dto.RegisterRequest;
import spotify.Entity.Person;
import spotify.Entity.Role_Person;
import spotify.Mapper.PersonListMapper;
import spotify.Mapper.PersonMapper;
import spotify.Repository.PersonRepository;
import spotify.Repository.RoleRepository;
import spotify.Service.PersonService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private PersonListMapper personListMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "password");
        Person person = new Person();
        person.setEmail("test@example.com");
        person.setPassword("encodedPassword");
        Role_Person role = new Role_Person();
        role.setRole("ROLE_USER");
        person.setRole(role);

        when(personRepository.findByEmail("test@example.com")).thenReturn(person);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);

        String token = personService.login(request);

        assertNotNull(token);
        verify(personRepository).findByEmail("test@example.com");
        verify(passwordEncoder).matches("password", "encodedPassword");
    }

    @Test
    void testLogin_UserNotFound() {
        AuthenticationRequest request = new AuthenticationRequest("notfound@example.com", "password");

        when(personRepository.findByEmail("notfound@example.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> personService.login(request));
    }

    @Test
    void testLogin_InvalidPassword() {
        AuthenticationRequest request = new AuthenticationRequest("test@example.com", "wrongPassword");
        Person person = new Person();
        person.setEmail("test@example.com");
        person.setPassword("encodedPassword");

        when(personRepository.findByEmail("test@example.com")).thenReturn(person);
        when(passwordEncoder.matches("wrongPassword", "encodedPassword")).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> personService.login(request));
    }

    @Test
    void testRegister_Success() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("newuser@example.com");
        request.setPassword("password");
        request.setPhone("123456789");

        Role_Person role = new Role_Person();
        role.setId(2L);
        role.setRole("ROLE_USER");

        Person person = new Person();

        when(personRepository.existsByEmail("newuser@example.com")).thenReturn(false);
        when(roleRepository.findById(2L)).thenReturn(Optional.of(role));
        when(personMapper.toModel(request)).thenReturn(person);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        String result = personService.Register(request);

        assertEquals("Register Success", result);
        verify(personRepository).save(person);
    }

    @Test
    void testRegister_UserAlreadyExists() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("existing@example.com");

        when(personRepository.existsByEmail("existing@example.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> personService.Register(request));
    }

    @Test
    void testGetAll() {
        List<Person> personList = Collections.singletonList(new Person());
        List<PersonDto> personDtoList = Collections.singletonList(new PersonDto());

        when(personRepository.findAll()).thenReturn(personList);
        when(personListMapper.toDtoList(personList)).thenReturn(personDtoList);

        List<PersonDto> result = personService.getAll();

        assertEquals(1, result.size());
        verify(personRepository).findAll();
    }

    @Test
    void testUpdatePerson_Success() {
        Long id = 1L;
        RegisterRequest updatedRequest = new RegisterRequest();
        updatedRequest.setName("Updated Name");

        Person person = new Person();
        person.setId(id);

        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        String result = personService.updatePerson(id, updatedRequest);

        assertEquals("Update Success", result);
        assertEquals("Updated Name", person.getName());
        verify(personRepository).save(person);
    }

    @Test
    void testDeletePerson_Success() {
        Long id = 1L;

        when(personRepository.existsById(id)).thenReturn(true);

        personService.deletePerson(id);

        verify(personRepository).deleteById(id);
    }

    @Test
    void testDeletePerson_NotFound() {
        Long id = 1L;

        when(personRepository.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> personService.deletePerson(id));
    }
}
