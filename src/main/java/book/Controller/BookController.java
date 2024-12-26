package book.Controller;

import book.Dto.BookDto;
import book.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
  private final BookService service;

  //  Get All Books
  @GetMapping
  public List<BookDto> getAllBooks() {
      return service.getAllBooks();
  }

  //  Get Book By ID
  @GetMapping(value = "/{id}")
  public BookDto getBookById(@PathVariable Long id) {
      return service.getBookById(id);
  }

  //  Add new Book
  @PostMapping
  public void addBook(@RequestBody BookDto bookDto) {
    service.addBook(bookDto);
  }

  //  Update Book
  @PutMapping(value = "/{id}")
  public String updateBook(@PathVariable Long id, @RequestBody BookDto updatedBook) {
     service.updatedBook(id, updatedBook);
     return "Book updated";
  }

  @DeleteMapping(value = "/{id}")
  public void deletaBook(@PathVariable Long id) {
    service.deleteBook(id);
  }
}
