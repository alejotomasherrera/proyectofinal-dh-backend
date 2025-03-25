package digitalMonyHouse.servicio_transacciones.infrastructure.feign;

import digitalMonyHouse.servicio_transacciones.infrastructure.web.request.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-tarjetas", url="http://proyectofinal-dh-backend-servicio-tarjetas-1:8086/api")
public interface CardClient {
    @GetMapping("/accounts/{userId}/cards/by-number/{cardNumber}")
    CardDTO getCardByLastFourNumbers(@PathVariable("userId") Long userId, @PathVariable("cardNumber") String cardNumber);
}
