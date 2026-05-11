package saifi.net.contratsassuranceprojet.services;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import saifi.net.contratsassuranceprojet.dto.*;
import saifi.net.contratsassuranceprojet.entities.*;
import saifi.net.contratsassuranceprojet.enums.StatusContrat;
import saifi.net.contratsassuranceprojet.mappers.AssuranceMapper;
import saifi.net.contratsassuranceprojet.repositories.*;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class AssuranceServiceImpl implements IAssuranceService {

    private final ClientRepository clientRepository;
    private final ContratRepository contratRepository;
    private final ContractAutoRepository contratAutoRepository;
    private final ContratHabitationRepository contratHabitationRepository;
    private final ContratSanteRepository contratSanteRepository;
    private final PaiementRepository paiementRepository;
    private final AssuranceMapper mapper;

    // Pour Clients

    @Override
    public ClientDTO saveClient(ClientDTO dto) {
        Client client = mapper.toClient(dto);
        return mapper.toClientDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + id));
        return mapper.toClientDTO(client);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + id));
        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        return mapper.toClientDTO(clientRepository.save(client));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(mapper::toClientDTO)
                .collect(Collectors.toList());
    }

    // Pour Contract

    @Override
    public ContratDTO getContrat(Long id) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé : " + id));
        return mapper.toContratDTO(contrat);
    }

    @Override
    public List<ContratDTO> getContratsClient(Long clientId) {
        return contratRepository.findByClientId(clientId).stream()
                .map(mapper::toContratDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratDTO> getAllContrats() {
        return contratRepository.findAll().stream()
                .map(mapper::toContratDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public ContratDTO updateStatut(Long id, StatusContrat statut) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé : " + id));
        contrat.setStatut(statut);
        return mapper.toContratDTO(contratRepository.save(contrat));
    }

    // Pour Contrat Auto

    @Override
    public ContratAutoDTO saveContratAuto(ContratAutoDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + dto.getClientId()));
        ContratAuto contrat = new ContratAuto();
        contrat.setClient(client);
        contrat.setDateSouscription(dto.getDateSouscription());
        contrat.setStatut(dto.getStatut());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setDureeContrat(dto.getDureeContrat());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        contrat.setNumeroImmatriculation(dto.getNumeroImmatriculation());
        contrat.setMarque(dto.getMarque());
        contrat.setModele(dto.getModele());
        return mapper.toContratAutoDTO(contratAutoRepository.save(contrat));
    }

    @Override
    public ContratAutoDTO updateContratAuto(Long id, ContratAutoDTO dto) {
        ContratAuto contrat = contratAutoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat auto non trouvé : " + id));
        contrat.setStatut(dto.getStatut());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        contrat.setNumeroImmatriculation(dto.getNumeroImmatriculation());
        contrat.setMarque(dto.getMarque());
        contrat.setModele(dto.getModele());
        return mapper.toContratAutoDTO(contratAutoRepository.save(contrat));
    }

    // Pour Contrat Habitation

    @Override
    public ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + dto.getClientId()));
        ContratHabitation contrat = new ContratHabitation();
        contrat.setClient(client);
        contrat.setDateSouscription(dto.getDateSouscription());
        contrat.setStatut(dto.getStatut());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setDureeContrat(dto.getDureeContrat());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        contrat.setTypeLogement(dto.getTypeLogement());
        contrat.setAdresse(dto.getAdresse());
        contrat.setSuperficie(dto.getSuperficie());
        return mapper.toContratHabitationDTO(contratHabitationRepository.save(contrat));
    }

    @Override
    public ContratHabitationDTO updateContratHabitation(Long id, ContratHabitationDTO dto) {
        ContratHabitation contrat = contratHabitationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat habitation non trouvé : " + id));
        contrat.setStatut(dto.getStatut());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        contrat.setTypeLogement(dto.getTypeLogement());
        contrat.setAdresse(dto.getAdresse());
        contrat.setSuperficie(dto.getSuperficie());
        return mapper.toContratHabitationDTO(contratHabitationRepository.save(contrat));
    }

    // Pour Contrat Santé

    @Override
    public ContratSanteDTO saveContratSante(ContratSanteDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé : " + dto.getClientId()));
        ContratSante contrat = new ContratSante();
        contrat.setClient(client);
        contrat.setDateSouscription(dto.getDateSouscription());
        contrat.setStatut(dto.getStatut());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setDureeContrat(dto.getDureeContrat());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        contrat.setNiveauCouverture(dto.getNiveauCouverture());
        contrat.setNombrePersonnesCouvertes(dto.getNombrePersonnesCouvertes());
        return mapper.toContratSanteDTO(contratSanteRepository.save(contrat));
    }

    @Override
    public ContratSanteDTO updateContratSante(Long id, ContratSanteDTO dto) {
        ContratSante contrat = contratSanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat santé non trouvé : " + id));
        contrat.setStatut(dto.getStatut());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        contrat.setNiveauCouverture(dto.getNiveauCouverture());
        contrat.setNombrePersonnesCouvertes(dto.getNombrePersonnesCouvertes());
        return mapper.toContratSanteDTO(contratSanteRepository.save(contrat));
    }

    // Pour Paiements

    @Override
    public PaiementDTO savePaiement(PaiementDTO dto) {
        Contrat contrat = contratRepository.findById(dto.getContratId())
                .orElseThrow(() -> new RuntimeException("Contrat non trouvé : " + dto.getContratId()));
        Paiement paiement = Paiement.builder()
                .date(dto.getDate())
                .montant(dto.getMontant())
                .type(dto.getType())
                .contrat(contrat)
                .build();
        return mapper.toPaiementDTO(paiementRepository.save(paiement));
    }

    @Override
    public PaiementDTO getPaiement(Long id) {
        return mapper.toPaiementDTO(paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé : " + id)));
    }

    @Override
    public List<PaiementDTO> getPaiementsContrat(Long contratId) {
        return paiementRepository.findByContratId(contratId).stream()
                .map(mapper::toPaiementDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }

    @Override
    public List<PaiementDTO> getAllPaiements() {
        return paiementRepository.findAll().stream()
                .map(mapper::toPaiementDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<ContratAutoDTO> getAllContratsAuto() {
        return contratAutoRepository.findAll().stream()
                .map(mapper::toContratAutoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratHabitationDTO> getAllContratsHabitation() {
        return contratHabitationRepository.findAll().stream()
                .map(mapper::toContratHabitationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContratSanteDTO> getAllContratsSante() {
        return contratSanteRepository.findAll().stream()
                .map(mapper::toContratSanteDTO)
                .collect(Collectors.toList());
    }

}
