package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.LowonganModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class LowonganServiceImpl implements LowonganService {
    @Autowired
    private LowonganDb lowonganDb;

    @Override
    public Optional<LowonganModel> getLowonganById(Integer id) {
        return lowonganDb.findById(id).get();
    }
}
