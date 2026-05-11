package saifi.net.contratsassuranceprojet.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import saifi.net.contratsassuranceprojet.enums.StatusContrat;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateSouscription;

    @Enumerated(EnumType.STRING)
    private StatusContrat statut;

    private LocalDate dateValidation;

    private Double montantCotisation;

    private Integer dureeContrat;

    private Double tauxCouverture;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Paiement> paiements;
}
