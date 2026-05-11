package saifi.net.contratsassuranceprojet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saifi.net.contratsassuranceprojet.entities.Paiement;
import saifi.net.contratsassuranceprojet.enums.TypePaiement;

import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByContratId(Long contratId);
    List<Paiement> findByType(TypePaiement type);
}

