package my.project.ttn_3.DTO.ttns;

import my.project.ttn_3.entity.Customers;
import my.project.ttn_3.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TtnResponse {
    private Integer id;
    private Customers customer;
    private Orders order;
    private String accessKey;
    private LocalDate dateCreate;
}
