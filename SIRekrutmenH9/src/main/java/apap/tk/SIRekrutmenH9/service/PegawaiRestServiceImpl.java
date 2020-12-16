package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.rest.BaseResponse;
import apap.tk.SIRekrutmenH9.rest.PegawaiData;
import apap.tk.SIRekrutmenH9.rest.Setting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;

@Service
@Transactional
public class PegawaiRestServiceImpl implements PegawaiRestService{
    private final WebClient webClient;

    public PegawaiRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder
                .baseUrl(Setting.pegawaiAPI).build();
    }

    @Override
    public BaseResponse addPegawaiData(PegawaiData pegawai) {
        return this.webClient
                .post()
                .uri("/api/v1/pegawai")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(pegawai)
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();
    }


    @Override
    public BaseResponse getPegawaiData(String username) {
        return this.webClient
                .get()
                .uri("/api/v1/pegawai/" + username)
                .retrieve()
                .bodyToMono(BaseResponse.class)
                .block();
    }
}
