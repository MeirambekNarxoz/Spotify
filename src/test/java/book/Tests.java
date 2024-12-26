package book;

import book.Dto.BookDto;
import book.Entity.Book;
import book.Mapper.BookMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Tests {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void test() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("1+1");
        book.setAuthor("author");
        book.setYear(2020);
        book.setGenre("genre");

        BookDto newDto = bookMapper.toDto(book);

        BookDto expectedDto = new BookDto();
        expectedDto.setId(1L);
        expectedDto.setTitle("1+1");
        expectedDto.setAuthor("author");
        expectedDto.setYear(2020);
        expectedDto.setCategory("genre");

        Assertions.assertEquals(expectedDto.getId(), newDto.getId());
        Assertions.assertEquals(expectedDto.getTitle(), newDto.getTitle());
        Assertions.assertEquals(expectedDto.getAuthor(), newDto.getAuthor());
        Assertions.assertEquals(expectedDto.getYear(), newDto.getYear());
        Assertions.assertEquals(expectedDto.getCategory(), newDto.getCategory());
    }
    @Test
    public void test2() {
        System.out.println("Hello World");
    }
}
