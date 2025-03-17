package digitalMonyHouse.servicio_usuarios.application.service;

import digitalMonyHouse.servicio_usuarios.application.port.UserRepository;
import digitalMonyHouse.servicio_usuarios.domain.model.User;
import digitalMonyHouse.servicio_usuarios.exception.BadRequestException;
import digitalMonyHouse.servicio_usuarios.exception.ResourceNotFoundException;
import digitalMonyHouse.servicio_usuarios.infrastructure.feign.AccountServiceClient;
import digitalMonyHouse.servicio_usuarios.infrastructure.web.response.AccountCreatedDTO;
import digitalMonyHouse.servicio_usuarios.infrastructure.web.response.AccountToCreateDTO;
import digitalMonyHouse.servicio_usuarios.infrastructure.web.response.UserDTO;
import digitalMonyHouse.servicio_usuarios.infrastructure.web.response.UserRegisteredDTO;
import digitalMonyHouse.servicio_usuarios.infrastructure.web.response.UserRegistrationDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountServiceClient accountServiceClient;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AccountServiceClient accountServiceClient,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.accountServiceClient = accountServiceClient;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRegisteredDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        try {
            // Verificar si el correo ya está registrado
            if (userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
                throw new BadRequestException("Email already registered");
            }

            // Crear el objeto User usando el constructor
            User user = new User(userRegistrationDTO, passwordEncoder.encode(userRegistrationDTO.getPassword()));
            User savedUser = userRepository.save(user);

            // Crear la cuenta para el usuario a través de Feign
            AccountToCreateDTO accountToCreateDTO = new AccountToCreateDTO(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName());
            AccountCreatedDTO accountCreatedDTO = accountServiceClient.createAccount(accountToCreateDTO);

            return new UserRegisteredDTO(savedUser, accountCreatedDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error registering user: " + e.getMessage());
        }
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
        return new UserDTO(user);
    }

    public boolean userExists(Long userId) {
        return userRepository.existsById(userId);
    }


    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        user.update(userDTO);
        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
