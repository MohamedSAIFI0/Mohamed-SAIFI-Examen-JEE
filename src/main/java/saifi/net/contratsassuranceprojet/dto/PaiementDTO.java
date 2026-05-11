package saifi.net.contratsassuranceprojet.dto;

import lombok.*;
import saifi.net.contratsassuranceprojet.enums.TypePaiement;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PaiementDTO {
    private Long id;
    private LocalDate date;
    private Double montant;
    private TypePaiement type;
    private Long contratId;
}