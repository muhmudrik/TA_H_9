package apap.tk.SIRekrutmenH9.service;

import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tk.SIRekrutmenH9.rest.BaseResponse;
import apap.tk.SIRekrutmenH9.rest.PelatihanDetail;
import apap.tk.SIRekrutmenH9.rest.Setting;

@Service
@Transactional
public class PelatihanRestServiceImpl implements PelatihanRestService {
    private final WebClient webClient;

    public PelatihanRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder
                .baseUrl(Setting.pelatihanAPI).build();
    }

    @Override
    public BaseResponse<PelatihanDetail> addPelatihanBaru(PelatihanDetail pelatihanDetail) {
        return this.webClient
                .post()
                .uri("/api/v1/pelatihan/buat/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(pelatihanDetail)
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();
    }
    
}
