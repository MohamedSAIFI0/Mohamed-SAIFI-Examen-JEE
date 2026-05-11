package saifi.net.contratsassuranceprojet.dto;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class LoginResponseDTO {
    private String token;
    private String username;
    private String role;
}
