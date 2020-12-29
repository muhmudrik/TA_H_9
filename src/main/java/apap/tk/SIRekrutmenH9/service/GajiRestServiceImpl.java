package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.repository.UserDb;
import apap.tk.SIRekrutmenH9.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GajiRestServiceImpl implements GajiRestService{
   private final WebClient webClient;

    @Autowired
    UserDb userDb;

    public GajiRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(Setting.gajiUrl).build();
    }

    @Override
    public Mono<String> getGajiAll() {
        return this.webClient.get().uri("").retrieve().bodyToMono(String.class);
    }
}
