package saifi.net.contratsassuranceprojet.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("AUTO")
@PrimaryKeyJoinColumn(name = "id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratAuto extends Contrat {
    private String numeroImmatriculation;
    private String marque;
    private String modele;
}
