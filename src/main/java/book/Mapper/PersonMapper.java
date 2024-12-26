package book.Mapper;

import book.Dto.RegisterRequest;
import book.Entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(source = "phone",target = "phoneNumber")
    Person toModel(RegisterRequest request);
    @Mapping(source = "phoneNumber",target = "phone")
    RegisterRequest toDto(Person person);
    List<Person> toModelList(List<RegisterRequest> registerRequests);
    List<RegisterRequest> toDtoList(List<Person> persons);
}
