package saifi.net.contratsassuranceprojet.entities;
import jakarta.persistence.*;
import lombok.*;
import saifi.net.contratsassuranceprojet.enums.TypeLogement;


@Entity
@DiscriminatorValue("HABITATION")
@PrimaryKeyJoinColumn(name = "id")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratHabitation extends Contrat {
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;
    private String adresse;
    private Double superficie;
}
