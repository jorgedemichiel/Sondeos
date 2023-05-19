package Pruebas.test.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Pruebas.test.models.Book;
import Pruebas.test.repository.BookRepository;
import Pruebas.test.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {

   @Autowired
   private BookService bookService;

   @Autowired
   private BookRepository bookRepository;

   @GetMapping("/books")
   public ResponseEntity<Page<Book>> allBooks(@RequestParam("current_page") int currentPage, @RequestParam("size") int size) {

      return ResponseEntity.ok(bookService.getAllBooks(currentPage, size));
   }

   @PostMapping("/book")
   public ResponseEntity<Book> createBook(@RequestBody Book book) {
      Book bookSaved = bookService.createBook(book);
      return new ResponseEntity<>(bookSaved, HttpStatus.CREATED);
   }

   @PostMapping("/cargar")
   public ResponseEntity<String> loadBooks() {

      bookRepository.saveAll(
            Arrays.asList(new Book(1L, "1984", "George Orwell", 10.0, LocalDate.now()),
                  new Book(2L, "To Kill a Mockingbird", "Harper Lee", 15.0, LocalDate.now()),
                  new Book(3L, "Pride and Prejudice", "Jane Austen", 20.0, LocalDate.now()),
                  new Book(4L, "The Great Gatsby", "F. Scott Fitzgerald", 20.0, LocalDate.now()),
                  new Book(5L, "Moby-Dick", "Herman Melville", 20.0, LocalDate.now()),
                  new Book(6L, "The Catcher in the Rye", "J.D. Salinger", 20.0, LocalDate.now()),
                  new Book(7L, "To the Lighthouse", "Virginia Woolf", 20.0, LocalDate.now()),
                  new Book(8L, "Brave New World", "Aldous Huxley", 20.0, LocalDate.now()),
                  new Book(9L, "The Lord of the Rings", "J.R.R. Tolkien", 20.0, LocalDate.now()),
                  new Book(10L, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 20.0, LocalDate.now()),
                  new Book(11L, "The Chronicles of Narnia", "C.S. Lewis", 20.0, LocalDate.now()),
                  new Book(12L, "The Hobbit", "J.R.R. Tolkien", 20.0, LocalDate.now()),
                  new Book(13L, "Jane Eyre", "Charlotte Bronte", 20.0, LocalDate.now()),
                  new Book(14L, "Crime and Punishment", "Fyodor Dostoevsky", 20.0, LocalDate.now()),
                  new Book(15L, "The Odyssey", "Homer", 40.0, LocalDate.now())));

      return ResponseEntity.ok("Los libros se cargaron correctamente");

   }

   @PutMapping("/book/{id}")
   public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
      Optional<Book> updatedBook = bookService.updateBook(id, book);

      if (updatedBook.isPresent()) {
         return ResponseEntity.ok(updatedBook.get());
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   @DeleteMapping("/book/{id}")
   public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
      bookService.deleteBook(id);
      return ResponseEntity.noContent().build();
   }

}
