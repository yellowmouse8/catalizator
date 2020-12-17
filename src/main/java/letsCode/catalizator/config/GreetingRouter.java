package letsCode.catalizator.config;

import letsCode.catalizator.handlers.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import javax.xml.stream.events.Characters;
import java.util.Map;


@Configuration
public class GreetingRouter {


    @Bean
    public RouterFunction<ServerResponse> hello(GreetingHandler greetingHandler) {
        RequestPredicate route = RequestPredicates.GET("/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN));
        return RouterFunctions
                .route(route, greetingHandler::hello)
                /*.andRoute(RequestPredicates.GET("/"),
                        serverRequest -> {
                    String user = serverRequest.queryParam("user").orElse("Nobody");
                    return ServerResponse.ok().render("index", Map.of("user", user ));
                        });
                 */
                .andRoute(RequestPredicates.GET("/"), greetingHandler::index);

    }
}