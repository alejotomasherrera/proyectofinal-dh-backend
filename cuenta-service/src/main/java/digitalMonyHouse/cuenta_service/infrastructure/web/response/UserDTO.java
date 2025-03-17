package digitalMonyHouse.cuenta_service.infrastructure.web.response;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
}
