package my.project.ttn_3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name must be not blank")
    @Size(min = 3, message = "Name length must be > 3")
    private String name;
    @NotBlank(message = "Address must be not blank")
    @Size(min = 5, max = 15, message = "Address length must be between 5 and 15")
    private String address;
    @NotBlank(message = "Route must be not blank")
    @Size(min = 5, message = "Route length must be > 5")
    private String route;
    private String accessKey;
    @Column(updatable = false)
    private LocalDate dateVisit;
//    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Orders> ordersList;
}
