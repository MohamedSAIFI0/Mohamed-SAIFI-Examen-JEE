package saifi.net.contratsassuranceprojet.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import saifi.net.contratsassuranceprojet.dto.LoginRequestDTO;
import saifi.net.contratsassuranceprojet.dto.LoginResponseDTO;
import saifi.net.contratsassuranceprojet.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "Connexion — retourne un token JWT")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String role = userDetails.getAuthorities().iterator().next().getAuthority();
        String token = jwtUtil.generateToken(request.getUsername(), role);

        return ResponseEntity.ok(LoginResponseDTO.builder()
                .token(token)
                .username(request.getUsername())
                .role(role)
                .build());
    }
}
