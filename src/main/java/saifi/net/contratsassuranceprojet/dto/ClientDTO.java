package saifi.net.contratsassuranceprojet.dto;

import lombok.*;
import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ClientDTO {
    private Long id;
    private String nom;
    private String email;
}
