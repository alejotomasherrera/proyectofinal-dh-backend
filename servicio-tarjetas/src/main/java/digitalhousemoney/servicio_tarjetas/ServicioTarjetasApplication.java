package digitalhousemoney.servicio_tarjetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioTarjetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioTarjetasApplication.class, args);
	}

}
