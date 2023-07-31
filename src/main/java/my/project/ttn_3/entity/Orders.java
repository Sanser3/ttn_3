package my.project.ttn_3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ElementCollection
    @NotBlank(message = "Order not blank")
    private List<Integer> orders;
    @Column(updatable = false)
    private LocalDate dateCreate;
}
