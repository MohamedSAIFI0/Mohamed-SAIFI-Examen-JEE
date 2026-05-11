package saifi.net.contratsassuranceprojet.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = true)
public class ContratAutoDTO extends ContratDTO {
    private String numeroImmatriculation;
    private String marque;
    private String modele;
}