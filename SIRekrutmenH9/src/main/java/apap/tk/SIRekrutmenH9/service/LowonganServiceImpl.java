package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.LowonganModel;
import apap.tk.SIRekrutmenH9.repository.LowonganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LowonganServiceImpl implements LowonganService{
    @Autowired
    LowonganDB lowonganDB;

    @Override
    public List<LowonganModel> getLowonganList(){
        return lowonganDB.findAll();
    }

    @Override
    public LowonganModel getLowonganById(Integer id){
        return lowonganDB.findById(id).get();
    }

    @Override
    public LowonganModel ubahLowongan(LowonganModel lowongan) {
        lowonganDB.save(lowongan);
        return lowongan;
    }
}