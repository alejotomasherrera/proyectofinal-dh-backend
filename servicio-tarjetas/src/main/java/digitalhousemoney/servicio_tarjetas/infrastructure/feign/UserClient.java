package digitalhousemoney.servicio_tarjetas.infrastructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-usuarios", url = "http://servicio-usuarios:8083")
public interface UserClient {

    @GetMapping("api/users/exists/{id}")
    boolean userExists(@PathVariable Long id);
}
