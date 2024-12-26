package spotify.Mapper;

import spotify.Dto.BookDto;
import spotify.Entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "category", target = "genre")
    Book toModel(BookDto dto);
    @Mapping(source = "genre", target = "category")
    BookDto toDto(Book book);
    List<Book>  toModelList(List<BookDto> booksDtoList);
    List<BookDto> toDtoList(List<Book> books);

}
