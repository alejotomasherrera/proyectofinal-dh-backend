package digitalMonyHouse.servicio_transacciones.infrastructure.web.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {
    private String id;
    private String userId;
    private Double balance;
    private String cvu;
    private String alias;
    private String name;

}
