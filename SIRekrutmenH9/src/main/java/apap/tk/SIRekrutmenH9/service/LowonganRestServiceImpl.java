package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.LowonganModel;
import apap.tk.SIRekrutmenH9.repository.LowonganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LowonganRestServiceImpl implements LowonganRestService{

    @Autowired
    private LowonganDB lowonganDB;

    @Override
    public LowonganModel createLowongan(LowonganModel lowongan){
        return lowonganDB.save(lowongan);
    }
}
