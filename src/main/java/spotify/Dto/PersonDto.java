package spotify.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spotify.Entity.Role_Person;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private int id;
    private String email;
    private String role;
}
