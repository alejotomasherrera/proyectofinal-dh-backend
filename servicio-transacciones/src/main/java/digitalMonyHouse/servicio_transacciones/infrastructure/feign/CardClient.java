package digitalMonyHouse.servicio_transacciones.infrastructure.feign;

import digitalMonyHouse.servicio_transacciones.infrastructure.web.request.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-tarjetas")
public interface CardClient {
    @GetMapping("api/accounts/{userId}/cards/by-number/{cardNumber}")
    CardDTO getCardByLastFourNumbers(@PathVariable("userId") Long userId, @PathVariable("cardNumber") String cardNumber);
}
