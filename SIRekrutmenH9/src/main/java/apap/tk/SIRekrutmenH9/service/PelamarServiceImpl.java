package apap.tk.SIRekrutmenH9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tk.SIRekrutmenH9.model.PelamarModel;
import apap.tk.SIRekrutmenH9.repository.PelamarDb;

@Service
public class PelamarServiceImpl implements PelamarService {
    @Autowired
    private PelamarDb pelamarDb;

    @Override
    public void addPelamar(PelamarModel pelamar) {
        pelamarDb.save(pelamar);
    }

    @Override
    public void ubahInformasiPelamar(PelamarModel pelamar) {
        pelamarDb.save(pelamar);
    }

    @Override
    public PelamarModel getPelamar(Long idPelamar) {
        if(pelamarDb.findById(idPelamar).isEmpty()){
            return pelamarDb.findById(idPelamar).get();
        }else{
            return null;
        }
    }

}
