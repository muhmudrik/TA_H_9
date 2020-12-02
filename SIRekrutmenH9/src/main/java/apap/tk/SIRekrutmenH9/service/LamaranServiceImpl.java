package apap.tk.SIRekrutmenH9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tk.SIRekrutmenH9.model.LamaranModel;
import apap.tk.SIRekrutmenH9.repository.LamaranDb;

@Service
@Transactional
public class LamaranServiceImpl implements LamaranService{

    @Autowired
    private LamaranDb lamaranDb;

    @Override
    public void addLamaran(LamaranModel lamaran) {
        lamaranDb.save(lamaran);
    }
    
}
