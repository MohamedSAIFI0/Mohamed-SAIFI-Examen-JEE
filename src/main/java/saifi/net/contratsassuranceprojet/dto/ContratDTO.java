package saifi.net.contratsassuranceprojet.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import saifi.net.contratsassuranceprojet.enums.StatusContrat;

import java.time.LocalDate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typeContrat")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ContratAutoDTO.class, name = "AUTO"),
        @JsonSubTypes.Type(value = ContratHabitationDTO.class, name = "HABITATION"),
        @JsonSubTypes.Type(value = ContratSanteDTO.class, name = "SANTE")
})
@Data @NoArgsConstructor @AllArgsConstructor
public class ContratDTO {
    private Long id;
    private LocalDate dateSouscription;
    private StatusContrat statut;
    private LocalDate dateValidation;
    private Double montantCotisation;
    private Integer dureeContrat;
    private Double tauxCouverture;
    private Long clientId;
    private String clientNom;
}

