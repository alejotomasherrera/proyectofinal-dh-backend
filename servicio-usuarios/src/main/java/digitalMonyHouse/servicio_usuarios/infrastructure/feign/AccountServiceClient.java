package digitalMonyHouse.servicio_usuarios.infrastructure.feign;

import digitalMonyHouse.servicio_usuarios.infrastructure.web.response.AccountCreatedDTO;
import digitalMonyHouse.servicio_usuarios.infrastructure.web.response.AccountToCreateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cuenta-service", url = "http://proyectofinal-dh-backend-cuenta-service-1:8084/api")
public interface AccountServiceClient {

    @PostMapping("/create-account")
    AccountCreatedDTO createAccount(@RequestBody AccountToCreateDTO accountToCreateDTO);
}
