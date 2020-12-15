package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface GajiRestService {
    List<UserModel> retrieveListUser();

}
