package digitalMonyHouse.cuenta_service.infrastructure.web.request;

import lombok.Data;

@Data
public class AccountSearchRequest {
    private String cvu;
    private String alias;
}
