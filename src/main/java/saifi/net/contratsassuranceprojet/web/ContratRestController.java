package saifi.net.contratsassuranceprojet.web;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import saifi.net.contratsassuranceprojet.dto.ContratAutoDTO;
import saifi.net.contratsassuranceprojet.dto.ContratDTO;
import saifi.net.contratsassuranceprojet.dto.ContratHabitationDTO;
import saifi.net.contratsassuranceprojet.dto.ContratSanteDTO;
import saifi.net.contratsassuranceprojet.enums.StatusContrat;
import saifi.net.contratsassuranceprojet.services.IAssuranceService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@RequiredArgsConstructor
@Tag(name = "Contrats")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class ContratRestController {

    private final IAssuranceService service;


    @GetMapping
    @Operation(summary = "Lister tous les contrats")
    public ResponseEntity<List<ContratDTO>> getAllContrats() {
        return ResponseEntity.ok(service.getAllContrats());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un contrat par id")
    public ResponseEntity<ContratDTO> getContrat(@PathVariable Long id) {
        return ResponseEntity.ok(service.getContrat(id));
    }

    @PutMapping("/{id}/statut")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Changer le statut d'un contrat")
    public ResponseEntity<ContratDTO> updateStatut(@PathVariable Long id,
                                                   @RequestParam StatusContrat statut) {
        return ResponseEntity.ok(service.updateStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Supprimer un contrat")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        service.deleteContrat(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/auto")
    @Operation(summary = "Lister les contrats auto")
    public ResponseEntity<List<ContratAutoDTO>> getAllAuto() {
        return ResponseEntity.ok(service.getAllContratsAuto());
    }

    @PostMapping("/auto")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Créer un contrat auto")
    public ResponseEntity<ContratAutoDTO> saveAuto(@RequestBody ContratAutoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveContratAuto(dto));
    }

    @PutMapping("/auto/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Modifier un contrat auto")
    public ResponseEntity<ContratAutoDTO> updateAuto(@PathVariable Long id,
                                                     @RequestBody ContratAutoDTO dto) {
        return ResponseEntity.ok(service.updateContratAuto(id, dto));
    }


    @GetMapping("/habitation")
    @Operation(summary = "Lister les contrats habitation")
    public ResponseEntity<List<ContratHabitationDTO>> getAllHabitation() {
        return ResponseEntity.ok(service.getAllContratsHabitation());
    }

    @PostMapping("/habitation")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Créer un contrat habitation")
    public ResponseEntity<ContratHabitationDTO> saveHabitation(@RequestBody ContratHabitationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveContratHabitation(dto));
    }

    @PutMapping("/habitation/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Modifier un contrat habitation")
    public ResponseEntity<ContratHabitationDTO> updateHabitation(@PathVariable Long id,
                                                                 @RequestBody ContratHabitationDTO dto) {
        return ResponseEntity.ok(service.updateContratHabitation(id, dto));
    }


    @GetMapping("/sante")
    @Operation(summary = "Lister les contrats santé")
    public ResponseEntity<List<ContratSanteDTO>> getAllSante() {
        return ResponseEntity.ok(service.getAllContratsSante());
    }

    @PostMapping("/sante")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Créer un contrat santé")
    public ResponseEntity<ContratSanteDTO> saveSante(@RequestBody ContratSanteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveContratSante(dto));
    }

    @PutMapping("/sante/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Modifier un contrat santé")
    public ResponseEntity<ContratSanteDTO> updateSante(@PathVariable Long id,
                                                       @RequestBody ContratSanteDTO dto) {
        return ResponseEntity.ok(service.updateContratSante(id, dto));
    }
}

