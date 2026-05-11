package saifi.net.contratsassuranceprojet.dto;

import lombok.*;
import saifi.net.contratsassuranceprojet.enums.NiveauCouverture;

@Data @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(callSuper = true)
public class ContratSanteDTO extends ContratDTO {
    private NiveauCouverture niveauCouverture;
    private Integer nombrePersonnesCouvertes;
}