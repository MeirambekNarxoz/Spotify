package book.Service;

import book.Dto.BookDto;
import book.Entity.Book;
import book.Mapper.BookMapper;
import book.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    public BookDto addBook(BookDto bookDto) {
       return mapper.toDto(repository.save(mapper.toModel(bookDto)));
    }

    public List<BookDto> getAllBooks(){
        return mapper.toDtoList(repository.findAll());
    }

    public BookDto getBookById(Long id){
        return mapper.toDto(repository.findById(id).orElse(null ));
    }

    public String updatedBook(Long id, BookDto updatedBook) {
        if (repository.existsById(id)) {
            Book existingBook = repository.findById(id).orElseThrow();
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setYear(updatedBook.getYear());
            existingBook.setGenre(updatedBook.getCategory());
            repository.save(existingBook);
            return "Book updated successfully";
        }
        return "Book not found";
    }

    public boolean deleteBook(Long id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
