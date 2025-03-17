package digitalMoneyHouse.auth_service.infrastructure.web.dto.response;

import lombok.Data;

@Data
public class TokenResponseDTO {
    private String accessToken;
    private String message;

    public TokenResponseDTO(String accessToken) {
        this.accessToken = accessToken;
        this.message = null;
    }

    public TokenResponseDTO() {
        this.accessToken = null;
        this.message = null;
    }

    public TokenResponseDTO(String accessToken, String message) {
        this.accessToken = accessToken;
        this.message = message;
    }
}
