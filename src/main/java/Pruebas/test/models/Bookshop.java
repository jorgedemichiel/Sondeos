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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

   @Override
   public String toString() {
      return "Person [id=" + id + ", name=" + title + ", userName=" + userName + ", price=" + price + ", date=" + date + "]";
   }

   public void setTitle(String title) {
      this.title = title;
   }

}
