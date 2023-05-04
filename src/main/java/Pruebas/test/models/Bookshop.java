package Pruebas.test.models;

import java.awt.print.Book;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Bookshop {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String title;

   @Column(name = "user_name")
   private String userName;

   @Column(name = "price")
   private Double price;

   @Column(name = "date_registry")
   @CreatedDate
   private LocalDate date;

   public Bookshop() {

   }

   public Bookshop(Long id, String title, String userName, Double price, LocalDate date) {
      this.id = id;
      this.title = title;
      this.userName = userName;
      this.price = price;
      this.date = date;
   }

   @Override
   public String toString() {
      return "Person [id=" + id + ", name=" + title + ", userName=" + userName + ", price=" + price + ", date=" + date + "]";
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public void setPrice(Double price) {
      this.price = price;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public Book get() {
      return null;
   }
}
