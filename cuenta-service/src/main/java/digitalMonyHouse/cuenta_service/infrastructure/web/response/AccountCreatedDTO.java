package digitalMonyHouse.cuenta_service.infrastructure.web.response;

import digitalMonyHouse.cuenta_service.domain.model.Account;
import lombok.Data;

@Data
public class AccountCreatedDTO {
    private Long userId;
    private Double balance;
    private String cvu;
    private String alias;
    private String name;

    public AccountCreatedDTO(Account account){
        this.userId = account.getUserId();
        this.balance = account.getBalance();
        this.cvu = account.getCvu();
        this.alias = account.getAlias();
        this.name = account.getName();
    }

}
