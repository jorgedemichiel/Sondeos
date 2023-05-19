package Pruebas.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Pruebas.test.models.Book;
import Pruebas.test.repository.BookRepository;

@Service
@Transactional
public class BookService {
   private static final int PAGE_SIZE = 10;

   @Autowired
   private BookRepository repository;

   public Page<Book> getAllBooks(int currentPage, int size) {
      Pageable page = PageRequest.of(currentPage, size);

      return repository.findAll(page);

   }

   public Book createBook(Book book) {
      return repository.save(book);
   }

   public Optional<Book> updateBook(Long id, Book book) {
      Optional<Book> bookshop = repository.findById(id);

      if (bookshop.isPresent()) {
         Book updatedBook = bookshop.get();
         updatedBook.setTitle(book.getTitle());
         updatedBook.setAuthor(book.getAuthor());
         updatedBook.setPublishDate(book.getPublishDate());

         return Optional.of(repository.save(updatedBook));
      } else {
         return Optional.empty();
      }
   }

   public void deleteBook(Long id) {
      repository.deleteById(id);
   }
}
