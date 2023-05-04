package Pruebas.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import Pruebas.test.models.Bookshop;

public interface BookRepository extends JpaRepository<Bookshop, Long> {

   List<Bookshop> findByTitle(@Param("title") String title);
}
