package digitalMonyHouse.service_api_gateway.config;

import digitalMonyHouse.service_api_gateway.filter.post.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@Configuration
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    private static final String SECRET_TOKEN = "dGhpcyBpcyBhIHNhbXBsZSBzZWNyZXQgb3B0aW9uYWw=";

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Ruta para el auth-server
                .route("auth-server-swagger", r -> r.path("/auth-server/auth/swagger-ui.html")
                        .filters(f -> f
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN))
                        .uri("http://auth-service:8082/auth/swagger-ui.html"))
                .route("auth-server-logout", r -> r.path("/auth-server/api/logout")
                        .filters(f -> f
                                .filter(filter) // Aplica el filtro de autenticación
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/auth-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://auth-service:8082")) // URI del auth-server
                .route("auth-server-login", r -> r.path("/auth-server/api/login")
                        .filters(f -> f
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/auth-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://auth-service:8082")) // URI del auth-server
                // Ruta para el users-server
                .route("users-server-register", r -> r.path("/users-server/api/register")
                        .filters(f -> f
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/users-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://servicio-usuarios:8083")) // URI del users-server
                .route("users-server", r -> r.path("/users-server/api/user/{id}")
                        .filters(f -> f
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/users-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://servicio-usuarios:8083")) // URI del users-server
                .route("users-server", r -> r.path("/users-server/api/**")
                        .filters(f -> f
                                .filter(filter) // Aplica el filtro de autenticación
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/users-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://servicio-usuarios:8083")) // URI del users-server
                // Ruta para el accounts-server
                .route("accounts-server", r -> r.path("/accounts-server/api/**")
                        .filters(f -> f
                                .filter(filter) // Aplica el filtro de autenticación
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/accounts-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://cuenta-service:8084")) // URI del accounts-server
                // Ruta para el cards-server
                .route("cards-server", r -> r.path("/cards-server/api/**")
                        .filters(f -> f
                                .filter(filter) // Aplica el filtro de autenticación
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/cards-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://servicio-tarjetas:8086")) // URI del tarjetas service
                // Ruta para el activities-server
                .route("activities-server", r -> r.path("/activities-server/api/**")
                        .filters(f -> f
                                .filter(filter) // Aplica el filtro de autenticación
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/activities-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://servicio-transacciones:8085")) // URI del transacciones service
                .build();
    }

    @Bean
    public WebFilter corsWebFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "*");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "*");
            exchange.getResponse().getHeaders().add("Access-Control-Expose-Headers", "Authorization, X-Secret-Token");
            exchange.getResponse().getHeaders().add("Access-Control-Allow-Credentials", "true");

            if (CorsUtils.isPreFlightRequest(exchange.getRequest())) {
                exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.OK);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        };
    }
}