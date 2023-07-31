package my.project.ttn_3.DTO.ttns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TtnRequest {
    private String name;
    private String address;
    private String route;
    private String accessKey;
    private LocalDate date;
    private List<Integer> orders;
}
