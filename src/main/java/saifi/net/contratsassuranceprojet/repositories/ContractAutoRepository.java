package saifi.net.contratsassuranceprojet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saifi.net.contratsassuranceprojet.entities.ContratAuto;

import java.util.List;

public interface ContractAutoRepository extends JpaRepository<ContratAuto, Long> {
    List<ContratAuto> findByMarque(String marque);
    List<ContratAuto> findByNumeroImmatriculation(String numero);
}
