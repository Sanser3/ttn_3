package my.project.ttn_3.DTO.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private List<Integer> orders;
    private LocalDate dateCreate;
}
