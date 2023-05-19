package Pruebas.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Pruebas.test.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
