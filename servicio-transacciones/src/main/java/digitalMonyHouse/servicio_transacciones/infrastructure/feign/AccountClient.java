package digitalMonyHouse.servicio_transacciones.infrastructure.feign;


import digitalMonyHouse.servicio_transacciones.infrastructure.web.request.AccountSearchRequest;
import digitalMonyHouse.servicio_transacciones.infrastructure.web.request.TransferRequest;
import digitalMonyHouse.servicio_transacciones.infrastructure.web.response.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cuenta-service", url = "http://localhost:8084/api")
public interface AccountClient {

    @PutMapping("/accounts/{userId}/balance") // Updated path
    AccountDTO updateAccountBalance(@PathVariable("userId") Long userId, @RequestBody Double amount);

    @GetMapping("/accounts/{userId}")
    AccountDTO getAccountByUserId(@PathVariable("userId") Long userId);

    @PostMapping("/accounts/transfer")
    ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest);

    @PostMapping("/accounts/search") // Cambiado a POST
    ResponseEntity<AccountDTO> getAccountByCvuOrAlias(@RequestBody AccountSearchRequest request);
}
