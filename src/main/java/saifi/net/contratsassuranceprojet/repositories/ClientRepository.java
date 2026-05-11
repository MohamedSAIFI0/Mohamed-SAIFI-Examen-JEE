package saifi.net.contratsassuranceprojet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saifi.net.contratsassuranceprojet.entities.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    boolean existsByEmail(String email);
}