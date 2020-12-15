package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.repository.UserDb;
import apap.tk.SIRekrutmenH9.rest.setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
        this.webClient = webClientBuilder.baseUrl(setting.gajiUrl).build();
    }

    @Override
    public List<UserModel> retrieveListUser() {
        List<UserModel> userList = new ArrayList<>();
        for(UserModel user : userDb.findAll()){
            if(user.getRole().getId() != 7){

            }
        }
        return userList;
    }
}
