package saifi.net.contratsassuranceprojet.mappers;

import saifi.net.contratsassuranceprojet.dto.*;
import saifi.net.contratsassuranceprojet.entities.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AssuranceMapper {

    public ClientDTO toClientDTO(Client client) {
        if (client == null) return null;

        ClientDTO dto = new ClientDTO();
        BeanUtils.copyProperties(client, dto);

        return dto;
    }

    public Client toClient(ClientDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        BeanUtils.copyProperties(dto, client);

        return client;
    }

    public ContratDTO toContratDTO(Contrat contrat) {

        if (contrat instanceof ContratAuto ca)
            return toContratAutoDTO(ca);

        if (contrat instanceof ContratHabitation ch)
            return toContratHabitationDTO(ch);

        if (contrat instanceof ContratSante cs)
            return toContratSanteDTO(cs);

        return null;
    }

    public ContratAutoDTO toContratAutoDTO(ContratAuto contrat) {

        ContratAutoDTO dto = new ContratAutoDTO();

        BeanUtils.copyProperties(contrat, dto);

        fillClientInfos(contrat, dto);

        return dto;
    }

    public ContratHabitationDTO toContratHabitationDTO(ContratHabitation contrat) {

        ContratHabitationDTO dto = new ContratHabitationDTO();

        BeanUtils.copyProperties(contrat, dto);

        fillClientInfos(contrat, dto);

        return dto;
    }

    public ContratSanteDTO toContratSanteDTO(ContratSante contrat) {

        ContratSanteDTO dto = new ContratSanteDTO();

        BeanUtils.copyProperties(contrat, dto);

        fillClientInfos(contrat, dto);

        return dto;
    }

    public PaiementDTO toPaiementDTO(Paiement paiement) {

        if (paiement == null) return null;

        PaiementDTO dto = new PaiementDTO();

        BeanUtils.copyProperties(paiement, dto);

        if (paiement.getContrat() != null) {
            dto.setContratId(paiement.getContrat().getId());
        }

        return dto;
    }

    private void fillClientInfos(Contrat contrat, ContratDTO dto) {

        if (contrat.getClient() != null) {

            dto.setClientId(contrat.getClient().getId());

            dto.setClientNom(contrat.getClient().getNom());
        }
    }
}
