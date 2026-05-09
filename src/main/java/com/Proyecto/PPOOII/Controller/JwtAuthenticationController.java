package com.Proyecto.PPOOII.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Proyecto.PPOOII.Config.JWTAuthenticationConfig;
import com.Proyecto.PPOOII.Config.Model.JwtRequest;
import com.Proyecto.PPOOII.Config.Model.JwtResponse;
import com.Proyecto.PPOOII.Entities.Usuario;
import com.Proyecto.PPOOII.Services.UsuarioServiceImpl;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
	JWTAuthenticationConfig jwtAuthenticationConfig;
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	@Autowired
	@Qualifier("UsuarioService")
	private UsuarioServiceImpl usuarioServiceImp;
	

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(
		@org.springframework.web.bind.annotation.RequestBody JwtRequest authenticationRequest,
		@io.swagger.v3.oas.annotations.Parameter(
			            name = "APIkey",
            			required = true,
            			description = "API Key del usuario"
        )

        @RequestHeader("APIkey") String APIKey
		) throws Exception {
			System.out.println("********************************************************************");
    
			System.out.println("Username: " + authenticationRequest.getUsername());
    
			System.out.println("Password: " + authenticationRequest.getPassword());
    
			System.out.println("APIKey: " + APIKey);
    
			System.out.println("********************************************************************");

    
			// 🔴 1. Buscar usuario
    
			Usuario user = usuarioServiceImp
            	.findByUsernameANDAPIKey(authenticationRequest.getUsername(), APIKey);

    	// 🔴 2. Validar usuario
    	if (user == null) {
        	throw new Exception("Usuario no encontrado o APIKey inválida");
    	}

    	// 🔴 3. VALIDAR PASSWORD (ESTO TE FALTABA)
    	if (!user.getPassword().equals(authenticationRequest.getPassword())) {
        	throw new Exception("Password incorrecto");
    	}

    // 🔴 4. Cargar usuario (Spring Security)
    final UserDetails userDetails =
            jwtInMemoryUserDetailsService.loadUserByUsername(user.getId().getLogin());

    // 🔴 5. Generar token
    final String token =
            jwtAuthenticationConfig.getJWTToken(userDetails.getUsername());

    System.out.println("********************************************************************");
    System.out.println("TOKEN: " + token);
    System.out.println("********************************************************************");

    return ResponseEntity.ok(new JwtResponse(token));
}

}
