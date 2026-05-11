package saifi.net.contratsassuranceprojet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saifi.net.contratsassuranceprojet.entities.ContratSante;
import saifi.net.contratsassuranceprojet.enums.NiveauCouverture;

import java.util.List;

public interface ContratSanteRepository extends JpaRepository<ContratSante, Long> {
    List<ContratSante> findByNiveauCouverture(NiveauCouverture niveau);
}

