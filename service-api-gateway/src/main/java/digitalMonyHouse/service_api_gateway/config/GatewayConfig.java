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

    private static final String SECRET_TOKEN = "dGhpcyBpcyBhIHNhbXBsZSBzZWNyZXQgb3B0aW9uYWw="; // Define tu token secreto aquí

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Ruta para el auth-server
                .route("auth-server-logout", r -> r.path("/auth-server/api/logout")
                        .filters(f -> f
                                .filter(filter) // Aplica el filtro de autenticación
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/auth-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://localhost:8082")) // URI del auth-server
                .route("auth-server-login", r -> r.path("/auth-server/api/login")
                        .filters(f -> f
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/auth-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://localhost:8082")) // URI del auth-server
                // Ruta para el users-server
                .route("users-server-register", r -> r.path("/users-server/api/register")
                        .filters(f -> f
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/users-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://localhost:8083")) // URI del users-server
                .route("users-server", r -> r.path("/users-server/api/user/{id}")
                        .filters(f -> f
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/users-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://localhost:8083")) // URI del users-server
                .route("users-server", r -> r.path("/users-server/api/**")
                        .filters(f -> f
                                .filter(filter) // Aplica el filtro de autenticación
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/users-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://localhost:8083")) // URI del users-server
                // Ruta para el accounts-server
                .route("accounts-server", r -> r.path("/accounts-server/api/**")
                        .filters(f -> f
                                .filter(filter) // Aplica el filtro de autenticación
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/accounts-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://localhost:8084")) // URI del accounts-server
                // Ruta para el cards-server
                .route("cards-server", r -> r.path("/cards-server/api/**")
                        .filters(f -> f
                                .filter(filter) // Aplica el filtro de autenticación
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/cards-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://localhost:8087")) // URI del cards-server
                // Ruta para el activities-server
                .route("activities-server", r -> r.path("/activities-server/api/**")
                        .filters(f -> f
                                .filter(filter) // Aplica el filtro de autenticación
                                .addRequestHeader("X-Secret-Token", SECRET_TOKEN) // Agrega el encabezado
                                .rewritePath("/activities-server/(?<segment>.*)", "/${segment}")) // Reescribe la ruta
                        .uri("http://localhost:8086")) // URI del activities-server
                .build();
    }

    @Bean
    public WebFilter corsWebFilter() {
        return (ServerWebExchange exchange, WebFilterChain chain) -> {
            if (CorsUtils.isCorsRequest(exchange.getRequest())) {
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
                exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Secret-Token");
                if (exchange.getRequest().getMethod().name().equals("OPTIONS")) {
                    exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.OK);
                    return exchange.getResponse().setComplete();
                }
            }
            return chain.filter(exchange);
        };
    }
}