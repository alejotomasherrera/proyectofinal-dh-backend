package digitalMonyHouse.servicio_usuarios.infrastructure.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountToCreateDTO {
    private Long userId;
    private String firstName;
    private String lastName;
}
