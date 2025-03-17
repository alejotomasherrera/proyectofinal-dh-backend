package digitalMoneyHouse.auth_service.application.service;

import digitalMoneyHouse.auth_service.application.port.UserRepository;
import digitalMoneyHouse.auth_service.domain.model.User;
import digitalMoneyHouse.auth_service.exception.BadRequestException;
import digitalMoneyHouse.auth_service.exception.ResourceNotFoundException;
import digitalMoneyHouse.auth_service.infrastructure.web.dto.response.TokenResponseDTO;
import digitalMoneyHouse.auth_service.infrastructure.web.dto.response.UserLoginDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, String> redisTemplate;

    public AuthService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, RedisTemplate<String, String> redisTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTemplate = redisTemplate;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public TokenResponseDTO loginUser(UserLoginDTO loginRequestDTO) {
        try {
            User user = this.findByEmail(loginRequestDTO.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + loginRequestDTO.getEmail()));


            if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
                throw new BadRequestException("Incorrect password");
            }

            String accessToken = jwtTokenProvider.generateAccessToken(user);

            return new TokenResponseDTO(accessToken);
        } catch (BadRequestException | ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error during login: " + e.getMessage());
        }
    }

    public void logoutUser(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7); // Remover el prefijo "Bearer "
        }

        if (!jwtTokenProvider.isTokenExpired(token)) {
            long expirationInSeconds = jwtTokenProvider.getExpirationDuration(token);
            redisTemplate.opsForValue().set(token, "invalidated", Duration.ofSeconds(expirationInSeconds));
        }
    }

}
