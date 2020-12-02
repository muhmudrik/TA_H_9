package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.LowonganModel;
import apap.tk.SIRekrutmenH9.repository.LowonganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LowonganServiceImpl implements LowonganService {
    @Autowired
    private LowonganDB lowonganDb;

    @Override
    public LowonganModel ubahLowongan(LowonganModel lowongan) {

    }
}