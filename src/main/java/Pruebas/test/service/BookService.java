package Pruebas.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Pruebas.test.models.Bookshop;
import Pruebas.test.repository.BookRepository;

@Service
public class BookService {

   @Autowired
   private BookRepository repository;

   public List<Bookshop> getAllBooks() {
      return repository.findAll();
   }

   public List<Bookshop> findByTitle(String title) {
      return repository.findByTitle(title);
   }

   public Bookshop createBook(Bookshop book) {
      return repository.save(book);
   }

   public Optional<Bookshop> updateBook(Long id, Bookshop book) {
      Optional<Bookshop> bookshop = repository.findById(id);

      if (bookshop.isPresent()) {
         Bookshop updatedBook = bookshop.get();
         updatedBook.setTitle(book.getTitle());
         updatedBook.setUserName(book.getUserName());
         updatedBook.setDate(book.getDate());

         return Optional.of(repository.save(updatedBook));
      } else {
         return Optional.empty();
      }
   }

   public void deleteBook(Long id) {
      repository.deleteById(id);
   }
}
