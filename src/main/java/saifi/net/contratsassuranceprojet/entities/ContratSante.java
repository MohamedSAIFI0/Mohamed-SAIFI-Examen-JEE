package saifi.net.contratsassuranceprojet.entities;
import jakarta.persistence.*;
import lombok.*;
import saifi.net.contratsassuranceprojet.enums.NiveauCouverture;

@Entity
@DiscriminatorValue("SANTE")
@PrimaryKeyJoinColumn(name = "id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ContratSante extends Contrat {
    @Enumerated(EnumType.STRING)
    private NiveauCouverture niveauCouverture;
    private Integer nombrePersonnesCouvertes;
}
