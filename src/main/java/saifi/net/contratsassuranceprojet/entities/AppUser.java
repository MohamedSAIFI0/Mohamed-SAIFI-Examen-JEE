package saifi.net.contratsassuranceprojet.entities;
import jakarta.persistence.*;
import lombok.*;
import saifi.net.contratsassuranceprojet.enums.RoleUtilisateur;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleUtilisateur role;

    private boolean enabled = true;
}

