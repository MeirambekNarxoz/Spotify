package spotify.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spotify.Dto.PersonDto;
import spotify.Entity.Person;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonListMapper {

    @Mapping(source = "role.role", target = "role") // Маппинг для преобразования объекта Role_Person в строку
    PersonDto toDto(Person person);

    @Mapping(source = "role", target = "role.role") // Обратный маппинг: строка в объект Role_Person
    Person toModel(PersonDto dto);

    List<PersonDto> toDtoList(List<Person> persons);

    List<Person> toModelList(List<PersonDto> dtoList);
}
