package my.project.ttn_3.DTO.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String name;
    private String address;
    private String route;
    private String accessKey;
}
