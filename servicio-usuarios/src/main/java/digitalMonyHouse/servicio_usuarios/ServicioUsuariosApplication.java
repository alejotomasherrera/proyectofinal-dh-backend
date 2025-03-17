package digitalMonyHouse.servicio_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioUsuariosApplication.class, args);
	}

}
