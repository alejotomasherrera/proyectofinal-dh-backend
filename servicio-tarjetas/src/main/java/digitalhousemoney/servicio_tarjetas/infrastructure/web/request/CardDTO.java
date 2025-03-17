package digitalhousemoney.servicio_tarjetas.infrastructure.web.request;

import digitalhousemoney.servicio_tarjetas.domain.models.Card;
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

    // Constructor
    public CardDTO(Card card) {
        this.id = card.getId();
        this.expiration = card.getExpiration();
        this.number = card.getNumber();
        this.name = card.getName();
        this.userId = card.getUserId();
    }
}
