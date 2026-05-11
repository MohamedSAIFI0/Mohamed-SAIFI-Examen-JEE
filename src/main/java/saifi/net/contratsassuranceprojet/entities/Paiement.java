package saifi.net.contratsassuranceprojet.entities;
import jakarta.persistence.*;
import lombok.*;
import saifi.net.contratsassuranceprojet.enums.TypePaiement;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Double montant;

    @Enumerated(EnumType.STRING)
    private TypePaiement type;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    @SuppressWarnings("JpaAttributeTypeInspection")
    private Contrat contrat;
}
