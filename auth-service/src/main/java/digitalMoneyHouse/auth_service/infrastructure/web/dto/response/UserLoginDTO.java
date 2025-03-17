package digitalMoneyHouse.auth_service.infrastructure.web.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


// Dto uso para login nomas no pa otra cosa
@Data
@NoArgsConstructor
public class UserLoginDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
