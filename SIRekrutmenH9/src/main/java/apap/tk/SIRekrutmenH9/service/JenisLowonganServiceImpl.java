package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.JenisLowonganModel;
import apap.tk.SIRekrutmenH9.repository.JenisLowonganDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JenisLowonganServiceImpl implements JenisLowonganService{

    @Autowired
    private JenisLowonganDB jenisLowonganDB;

    @Override
    public List<JenisLowonganModel> getAll(){
        return jenisLowonganDB.findAll();
    }
}
