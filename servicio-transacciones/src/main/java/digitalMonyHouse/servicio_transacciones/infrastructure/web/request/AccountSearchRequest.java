package digitalMonyHouse.servicio_transacciones.infrastructure.web.request;

import lombok.Data;

@Data
public class AccountSearchRequest {
    private String cvu;
    private String alias;
}
