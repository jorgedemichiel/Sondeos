package Pruebas.test.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import Pruebas.test.models.Book;
import Pruebas.test.repository.BookRepository;
import Pruebas.test.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

   @InjectMocks
   private BookService bookService;
   @Mock
   private BookRepository bookRepository;

   @Test
   public void testGetAllBooks() {
      List<Book> books = Arrays.asList(
            new Book(1L, "1984", "George Orwell", 10.0, LocalDate.now()),
            new Book(2L, "To Kill a Mockingbird", "Harper Lee", 15.0, LocalDate.now()));
      Pageable page = PageRequest.of(0, 10);
      Page<Book> expectedPage = new PageImpl<>(books, page, books.size());

      Mockito.when(bookRepository.findAll(page)).thenReturn(expectedPage);
      Page<Book> result = bookService.getAllBooks(0, 10);

      Assert.assertEquals(expectedPage, result);
   }

   @Test
   public void testCreateBook() {
      Book book = new Book(1L, "1984", "George Orwell", 10.0, LocalDate.now());

      Mockito.when(bookRepository.save(book)).thenReturn(book);
      Book result = bookService.createBook(book);

      Assert.assertEquals(book, result);
   }

   @Test
   public void testUpdateBook_NotFound() {
      Long id = 1L;
      Book updatedBook = new Book(id, "Animal Farm", "George Orwell", 12.0, LocalDate.now());

      Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());
      Optional<Book> result = bookService.updateBook(id, updatedBook);

      Assert.assertFalse(result.isPresent());
   }

   @Test
   public void testDeleteBook() {
      Long id = 1L;
      bookService.deleteBook(id);

      Mockito.verify(bookRepository, Mockito.times(1)).deleteById(id);
   }
}