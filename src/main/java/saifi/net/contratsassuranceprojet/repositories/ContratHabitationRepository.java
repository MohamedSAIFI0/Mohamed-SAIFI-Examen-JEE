package saifi.net.contratsassuranceprojet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saifi.net.contratsassuranceprojet.entities.ContratHabitation;
import saifi.net.contratsassuranceprojet.enums.TypeLogement;

import java.util.List;

public interface ContratHabitationRepository extends JpaRepository<ContratHabitation, Long> {
    List<ContratHabitation> findByTypeLogement(TypeLogement type);
}