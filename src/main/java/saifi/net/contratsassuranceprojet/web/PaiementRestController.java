package saifi.net.contratsassuranceprojet.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import saifi.net.contratsassuranceprojet.dto.PaiementDTO;
import saifi.net.contratsassuranceprojet.services.IAssuranceService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
@RequiredArgsConstructor
@Tag(name = "Paiements")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class PaiementRestController {

    private final IAssuranceService service;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Lister tous les paiements")
    public ResponseEntity<List<PaiementDTO>> getAllPaiements() {
        return ResponseEntity.ok(service.getAllPaiements());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un paiement par id")
    public ResponseEntity<PaiementDTO> getPaiement(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPaiement(id));
    }

    @GetMapping("/contrat/{contratId}")
    @Operation(summary = "Paiements d'un contrat")
    public ResponseEntity<List<PaiementDTO>> getByContrat(@PathVariable Long contratId) {
        return ResponseEntity.ok(service.getPaiementsContrat(contratId));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Enregistrer un paiement")
    public ResponseEntity<PaiementDTO> save(@RequestBody PaiementDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePaiement(dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Supprimer un paiement")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletePaiement(id);
        return ResponseEntity.noContent().build();
    }
}
