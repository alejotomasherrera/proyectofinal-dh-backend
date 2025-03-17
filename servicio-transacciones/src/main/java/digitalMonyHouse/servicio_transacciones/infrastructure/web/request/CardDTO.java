package digitalMonyHouse.servicio_transacciones.infrastructure.web.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardDTO {
    private Long id;
    private String expiration;
    private String number;
    private String name;
    private Long userId;

}
