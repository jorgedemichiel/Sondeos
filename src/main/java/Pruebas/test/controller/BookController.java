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
import Pruebas.test.repository.BookRepository;

@RestController
@RequestMapping("/api")
public class BookController {

   @Autowired
   private BookRepository repository;

   @GetMapping("/books")
   public List<Bookshop> allBooks(){
      return repository.findAll();
   }

   @GetMapping("/book/{title}")
   public List<Bookshop> findByTitle(@PathVariable("title") String title) {
      return repository.findByTitle(title);
   }

   @PostMapping("/book")
   public ResponseEntity<Bookshop> createBook(@RequestBody Bookshop book) {
     Bookshop bookSaved = repository.save(book);
      return new ResponseEntity<Bookshop>(bookSaved, HttpStatus.CREATED);
   }

   @PutMapping("/book/{id}")
   public ResponseEntity<Bookshop> updateBook(@PathVariable Long id, @RequestBody Bookshop book) {
      Optional<Bookshop> bookshop = repository.findById(id);

      if (bookshop.isPresent()) {
         Bookshop updatedBook = bookshop.get();
         updatedBook.setTitle(book.getTitle());
         updatedBook.setUserName(book.getUserName());
         updatedBook.setDate(book.getDate());

         Bookshop savedBook = repository.save(updatedBook);
         return ResponseEntity.ok(savedBook);
      } else {
         return ResponseEntity.notFound().build();
      }
   }


   @DeleteMapping("/book/{id}")
   public void deleteBook(@PathVariable("id") Long id) {
      repository.deleteById(id);
   }

}
