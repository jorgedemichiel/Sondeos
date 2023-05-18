package Pruebas.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Pruebas.test.models.Bookshop;
import Pruebas.test.service.BookService;

@RestController
@RequestMapping("/api")
public class BookController {

   @Autowired
   private BookService bookService;

   @GetMapping("/books")
   public List<Bookshop> allBooks() {
      return bookService.getAllBooks();
   }

   @GetMapping("/book/{title}")
   public List<Bookshop> findByTitle(@PathVariable("title") String title) {
      return bookService.findByTitle(title);
   }

   @PostMapping("/book")
   public ResponseEntity<Bookshop> createBook(@RequestBody Bookshop book) {
      Bookshop bookSaved = bookService.createBook(book);
      return new ResponseEntity<>(bookSaved, HttpStatus.CREATED);
   }

   @PutMapping("/book/{id}")
   public ResponseEntity<Bookshop> updateBook(@PathVariable Long id, @RequestBody Bookshop book) {
      Optional<Bookshop> updatedBook = bookService.updateBook(id, book);

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
