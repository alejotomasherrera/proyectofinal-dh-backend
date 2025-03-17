package digitalMonyHouse.servicio_usuarios.infrastructure.controllers;

import digitalMonyHouse.servicio_usuarios.application.service.UserService;
import digitalMonyHouse.servicio_usuarios.exception.BadRequestException;
import digitalMonyHouse.servicio_usuarios.exception.ResourceNotFoundException;
import digitalMonyHouse.servicio_usuarios.infrastructure.web.response.UserDTO;
import digitalMonyHouse.servicio_usuarios.infrastructure.web.response.UserRegisteredDTO;
import digitalMonyHouse.servicio_usuarios.infrastructure.web.response.UserRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisteredDTO> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        // Verificar si el correo ya está registrado
        userService.findByEmail(userRegistrationDTO.getEmail())
                .ifPresent(user -> {
                    throw new BadRequestException("Email already registered");
                });

        // Registrar el nuevo usuario
        UserRegisteredDTO registeredUser = userService.registerUser(userRegistrationDTO);


        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            UserDTO userDTO = userService.getUserById(id);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/exists/{id}")
    public Boolean userExists(@PathVariable Long id) {
        System.out.println("llego");
        return userService.userExists(id);
    }


    @PatchMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
