package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

//    @Mock
//    private BookRepository repository;
//    @Mock
//    private BookMapper mapper;
//    @InjectMocks
//    private BookService bookService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testAddBook() {
//        BookDto bookDto = new BookDto(null, "Test Title", "Test Author", 2021, "Fiction");
//        Book book = new Book(null, "Test Title", "Test Author", 2021, "Fiction");
//        Book savedBook = new Book(1L, "Test Title", "Test Author", 2021, "Fiction");
//        BookDto savedBookDto = new BookDto(1L, "Test Title", "Test Author", 2021, "Fiction");
//
//        when(mapper.toModel(bookDto)).thenReturn(book);
//        when(repository.save(book)).thenReturn(savedBook);
//        when(mapper.toDto(savedBook)).thenReturn(savedBookDto);
//
//        BookDto result = bookService.addBook(bookDto);
//
//        assertNotNull(result);
//        assertNotNull(result.getId());
//        assertEquals(1L, result.getId());
//        assertEquals("Test Title", result.getTitle());
//        assertEquals("Test Author", result.getAuthor());
//        verify(repository, times(1)).save(book);
//        verify(mapper, times(1)).toModel(bookDto);
//        verify(mapper, times(1)).toDto(savedBook);
//    }
//
//    @Test
//    void testGetAllBooks() {
//        Book book = new Book(1L, "Test Title", "Test Author", 2021, "Fiction");
//        BookDto bookDto = new BookDto(1L, "Test Title", "Test Author", 2021, "Fiction");
//        when(repository.findAll()).thenReturn(List.of(book));
//        when(mapper.toDtoList(List.of(book))).thenReturn(List.of(bookDto));
//
//        List<BookDto> result = bookService.getAllBooks();
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals("Test Title", result.get(0).getTitle());
//        verify(repository, times(1)).findAll();
//        verify(mapper, times(1)).toDtoList(List.of(book));
//    }
//
//    @Test
//    void testGetBookById() {
//        Long bookId = 1L;
//        Book book = new Book(1L, "Test Title", "Test Author", 2021, "Fiction");
//        BookDto bookDto = new BookDto(1L, "Test Title", "Test Author", 2021, "Fiction");
//        when(repository.findById(bookId)).thenReturn(Optional.of(book));
//        when(mapper.toDto(book)).thenReturn(bookDto);
//
//        BookDto result = bookService.getBookById(bookId);
//
//        assertNotNull(result);
//        assertEquals("Test Title", result.getTitle());
//        verify(repository, times(1)).findById(bookId);
//        verify(mapper, times(1)).toDto(book);
//    }
//
//    @Test
//    void testDeleteBook() {
//        Long bookId = 1L;
//        when(repository.existsById(bookId)).thenReturn(true);
//
//        boolean result = bookService.deleteBook(bookId);
//
//        assertTrue(result);
//        verify(repository, times(1)).existsById(bookId);
//        verify(repository, times(1)).deleteById(bookId);
//    }
//
//    @Test
//    void testUpdateBook() {
//        Long bookId = 1L;
//        Book existingBook = new Book(1L, "Old Title", "Old Author", 2000, "Drama");
//        BookDto updatedBookDto = new BookDto(1L, "New Title", "New Author", 2021, "Fiction");
//
//        when(repository.existsById(bookId)).thenReturn(true);
//        when(repository.findById(bookId)).thenReturn(Optional.of(existingBook));
//
//        String result = bookService.updatedBook(bookId, updatedBookDto);
//
//        assertEquals("Book updated successfully", result);
//        verify(repository, times(1)).save(existingBook);
//        assertEquals("New Title", existingBook.getTitle());
//    }

}
