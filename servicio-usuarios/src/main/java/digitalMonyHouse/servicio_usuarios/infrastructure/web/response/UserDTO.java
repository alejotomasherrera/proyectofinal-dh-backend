package digitalMonyHouse.servicio_usuarios.infrastructure.web.response;

import digitalMonyHouse.servicio_usuarios.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String phone;


    public UserDTO(User user) {
        this.id = user.getId().toString();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dni = user.getDni();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }
}
