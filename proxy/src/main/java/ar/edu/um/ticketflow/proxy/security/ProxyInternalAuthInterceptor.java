package ar.edu.um.ticketflow.proxy.security;

import ar.edu.um.ticketflow.proxy.config.BackendProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Mono;

@Component
public class ProxyInternalAuthInterceptor {

    private final BackendProperties backendProperties;

    public ProxyInternalAuthInterceptor(BackendProperties backendProperties) {
        this.backendProperties = backendProperties;
    }

    public ExchangeFilterFunction authFilter() {
        return (request, next) -> next.exchange(
                ClientRequest.from(request)
                        .header("Authorization", "Bearer " + backendProperties.getInternalToken())
                        .build()
        );
    }
}
