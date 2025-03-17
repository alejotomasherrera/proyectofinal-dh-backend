package digitalMonyHouse.servicio_transacciones.infrastructure.web.request;

import lombok.Data;

@Data
public class TransferRequest {
    private String origin;
    private String destination;
    private double amount;
    private String name;
    private String type;
}
