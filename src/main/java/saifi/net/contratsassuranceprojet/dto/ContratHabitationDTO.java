package saifi.net.contratsassuranceprojet.dto;

import lombok.*;
import saifi.net.contratsassuranceprojet.enums.TypeLogement;

@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = true)
public class ContratHabitationDTO extends ContratDTO {
    private TypeLogement typeLogement;
    private String adresse;
    private Double superficie;
}
