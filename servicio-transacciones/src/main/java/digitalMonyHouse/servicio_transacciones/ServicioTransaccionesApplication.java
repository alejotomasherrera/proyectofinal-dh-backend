package digitalMonyHouse.servicio_transacciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioTransaccionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioTransaccionesApplication.class, args);
	}

}
