package saifi.net.contratsassuranceprojet.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import saifi.net.contratsassuranceprojet.dto.ClientDTO;
import saifi.net.contratsassuranceprojet.services.IAssuranceService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Clients")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientRestController {

    private final IAssuranceService service;

    @GetMapping
    @Operation(summary = "Lister tous les clients")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(service.getAllClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un client par id")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(service.getClient(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Créer un client")
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveClient(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_EMPLOYE')")
    @Operation(summary = "Modifier un client")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id,
                                                  @RequestBody ClientDTO dto) {
        return ResponseEntity.ok(service.updateClient(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Supprimer un client")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        service.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/contrats")
    @Operation(summary = "Contrats d'un client")
    public ResponseEntity<?> getContratsClient(@PathVariable Long id) {
        return ResponseEntity.ok(service.getContratsClient(id));
    }
}
