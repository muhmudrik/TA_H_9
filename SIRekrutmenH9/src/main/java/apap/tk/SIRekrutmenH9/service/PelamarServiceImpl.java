package apap.tk.SIRekrutmenH9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tk.SIRekrutmenH9.model.PelamarModel;
import apap.tk.SIRekrutmenH9.repository.PelamarDb;

import javax.transaction.Transactional;

@Service
@Transactional
public class PelamarServiceImpl implements PelamarService {
    @Autowired
    private PelamarDb pelamarDb;

    @Override
    public void addPelamar(PelamarModel pelamar) {

        pelamarDb.save(pelamar);
    }
/*
    @Override
    public void ubahInformasiPelamar(PelamarModel pelamar) {

        pelamarDb.save(pelamar);
    }

    @Override
    public PelamarModel getPelamar(Integer idPelamar) {
        if(pelamarDb.findById(idPelamar).isEmpty()){
            return null;
        }else{
            return pelamarDb.findById(idPelamar).get();
        }
    }


 */
}
