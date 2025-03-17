package digitalhousemoney.servicio_tarjetas.infrastructure.web.response;

import digitalhousemoney.servicio_tarjetas.domain.models.Card;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardCreateDTO {
    private String expiration;
    private String number;
    private String name;
    private String cvc;
    private Long userId;

    public CardCreateDTO(String expiration, String number, String name, String cvc, Long userId) {
        this.expiration = expiration;
        this.number = number;
        this.name = name;
        this.cvc = cvc;
        this.userId = userId;
    }

    public CardCreateDTO(Card card) {
        this.expiration = card.getExpiration();
        this.number = card.getNumber();
        this.name = card.getName();
        this.cvc = card.getCvc();
        this.userId = card.getUserId();
    }
}
