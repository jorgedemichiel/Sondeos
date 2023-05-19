package Pruebas.test.models;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BOOKS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "TITLE")
   private String title;

   @Column(name = "AUTHOR")
   private String author;

   @Column(name = "PRICE")
   private Double price;

   @Column(name = "PUBLISH_DATE")
   private LocalDate publishDate;

   @Override
   public String toString() {
      return "Person [id=" + id + ", name=" + title + ", author=" + author + ", price=" + price + ", publish_date=" + publishDate + "]";
   }

}
