package Pruebas.test.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import Pruebas.test.models.Bookshop;
import Pruebas.test.repository.BookRepository;
import Pruebas.test.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

   @Mock
   private BookRepository repository;

   @InjectMocks
   private BookService bookService;

   @Test
   public void testGetAllBooks() {
      List<Bookshop> books = Arrays.asList(new Bookshop(1L, "Libro 1", "Usuario 1", 10.0, LocalDate.now()),
            new Bookshop(2L, "Libro 2", "Usuario 2", 15.0, LocalDate.now()),
            new Bookshop(3L, "Libro 3", "Usuario 3", 20.0, LocalDate.now()),
            new Bookshop(4L, "Libro 4", "Usuario 4", 25.0, LocalDate.now()));

      Mockito.when(repository.findAll()).thenReturn(books);
      List<Bookshop> result = bookService.getAllBooks();

      Assert.assertEquals(4, result.size());
      Assert.assertEquals("Libro 1", result.get(0).getTitle());
      Assert.assertEquals("Usuario 1", result.get(0).getUserName());
   }

   @Test
   public void testCreateBook() {
      Bookshop book = new Bookshop(null, "Libro nuevo", "Usuario 3", 20.0, LocalDate.now());

      Mockito.when(repository.save(Mockito.any(Bookshop.class))).thenAnswer(invocation -> {
         Bookshop bookArg = invocation.getArgument(0);
         bookArg.setId(1L);
         return bookArg;
      });

      Bookshop result = bookService.createBook(book);

      Assert.assertNotNull(result.getId());
      Assert.assertEquals("Libro nuevo", result.getTitle());
      Assert.assertEquals("Usuario 3", result.getUserName());
   }

   @Test
   public void testFindByTitle() {
      List<Bookshop> books = Arrays.asList(
            new Bookshop(1L, "Libro 1", "Usuario 1", 10.0, LocalDate.now()),
            new Bookshop(2L, "Libro 1", "Usuario 2", 15.0, LocalDate.now())
      );

      Mockito.when(repository.findByTitle("Libro 1")).thenReturn(books);

      List<Bookshop> result = bookService.findByTitle("Libro 1");

      Assert.assertEquals(2, result.size());
   }

   @Test
   public void testUpdateBook() {
      Bookshop book = new Bookshop(1L, "Libro 1", "Usuario 1", 10.0, LocalDate.now());

      Mockito.when(repository.findById(1L)).thenReturn(Optional.of(book));
      Mockito.when(repository.save(Mockito.any(Bookshop.class))).thenReturn(book);

      Optional<Bookshop> result = bookService.updateBook(1L, book);

      Assert.assertTrue(result.isPresent());
      Assert.assertEquals("Libro 1", result.get().getTitle());
      Assert.assertEquals("Usuario 1", result.get().getUserName());
   }

   @Test
   public void testUpdateBook_BookNotFound() {
      Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

      Optional<Bookshop> result = bookService.updateBook(1L, new Bookshop());

      Assert.assertFalse(result.isPresent());
   }

   @Test
   public void testDeleteBook() {
      bookService.deleteBook(1L);

      Mockito.verify(repository).deleteById(1L);
   }

}
