package my.project.ttn_3.DTO.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Integer id;
    private String name;
    private String address;
    private String route;
    private String accessKey;
    private LocalDate dateVisit;
}
