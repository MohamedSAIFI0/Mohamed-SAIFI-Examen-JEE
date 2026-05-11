package saifi.net.contratsassuranceprojet.services;

import saifi.net.contratsassuranceprojet.dto.*;
import saifi.net.contratsassuranceprojet.enums.StatusContrat;

import java.util.List;

public interface IAssuranceService {
    //Pour Client
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO getClient(Long id);
    ClientDTO updateClient(Long id, ClientDTO clientDTO);
    void deleteClient(Long id);
    List<ClientDTO> getAllClients();

    // Pour Contrats
    ContratDTO getContrat(Long id);
    List<ContratDTO> getContratsClient(Long clientId);
    List<ContratDTO> getAllContrats();
    void deleteContrat(Long id);
    ContratDTO updateStatut(Long id, StatusContrat statut);

    // Pour Contrat Auto
    ContratAutoDTO saveContratAuto(ContratAutoDTO dto);
    ContratAutoDTO updateContratAuto(Long id, ContratAutoDTO dto);

    // Pour Contrat Habitation
    ContratHabitationDTO saveContratHabitation(ContratHabitationDTO dto);
    ContratHabitationDTO updateContratHabitation(Long id, ContratHabitationDTO dto);

    // Pour Contrat Santé
    ContratSanteDTO saveContratSante(ContratSanteDTO dto);
    ContratSanteDTO updateContratSante(Long id, ContratSanteDTO dto);

    // Pour Paiements
    PaiementDTO savePaiement(PaiementDTO paiementDTO);
    PaiementDTO getPaiement(Long id);
    List<PaiementDTO> getPaiementsContrat(Long contratId);
    void deletePaiement(Long id);
    List<PaiementDTO> getAllPaiements();


    List<ContratAutoDTO> getAllContratsAuto();


    List<ContratHabitationDTO> getAllContratsHabitation();

    List<ContratSanteDTO> getAllContratsSante();


}
