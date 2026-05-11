package saifi.net.contratsassuranceprojet.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Contrat> contrats;
}
