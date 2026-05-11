package saifi.net.contratsassuranceprojet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saifi.net.contratsassuranceprojet.entities.Contrat;
import saifi.net.contratsassuranceprojet.enums.StatusContrat;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    List<Contrat> findByClientId(Long clientId);
    List<Contrat> findByStatut(StatusContrat statut);
}
