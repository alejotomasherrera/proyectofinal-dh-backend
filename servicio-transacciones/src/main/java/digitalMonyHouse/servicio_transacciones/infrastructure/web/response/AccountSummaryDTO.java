package digitalMonyHouse.servicio_transacciones.infrastructure.web.response;

import digitalMonyHouse.servicio_transacciones.infrastructure.web.request.ActivityDTO;
import lombok.Data;

import java.util.List;


@Data
public class AccountSummaryDTO {

    private Double balance;
    private List<ActivityDTO> activities;

    public AccountSummaryDTO(Double balance, List<ActivityDTO> activities) {
        this.balance = balance;
        this.activities = activities;
    }
}