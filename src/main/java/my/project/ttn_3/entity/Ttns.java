package my.project.ttn_3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ttns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "customers_id")
    private Customers customers;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;
    private String accessKey;
    @Column(updatable = false)
    private LocalDate createTtn;
}
