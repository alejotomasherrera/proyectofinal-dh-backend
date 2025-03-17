package digitalMoneyHouse.auth_service.infrastructure.controllers;

import digitalMoneyHouse.auth_service.application.service.AuthService;
import digitalMoneyHouse.auth_service.application.service.JwtTokenProvider;
import digitalMoneyHouse.auth_service.exception.BadRequestException;
import digitalMoneyHouse.auth_service.exception.ResourceNotFoundException;
import digitalMoneyHouse.auth_service.infrastructure.web.dto.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    private final JwtTokenProvider jwtTokenProvider;


    public AuthController(AuthService authService, JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> loginUser(@RequestBody UserLoginDTO loginRequestDTO) {
        try {
            // Autenticar al usuario y generar el token
            TokenResponseDTO tokenResponseDTO = authService.loginUser(loginRequestDTO);

            return new ResponseEntity<>(tokenResponseDTO, HttpStatus.OK);
        } catch (BadRequestException e) {
            // Manejar errores de credenciales incorrectas
            return new ResponseEntity<>(new TokenResponseDTO(null, e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            // Manejar errores de usuario no encontrado
            return new ResponseEntity<>(new TokenResponseDTO(null, e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Manejar otros errores
            return new ResponseEntity<>(new TokenResponseDTO(null, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestHeader("Authorization") String token) {
        try {
            authService.logoutUser(token);
            return new ResponseEntity<>("Logout successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error during logout: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}