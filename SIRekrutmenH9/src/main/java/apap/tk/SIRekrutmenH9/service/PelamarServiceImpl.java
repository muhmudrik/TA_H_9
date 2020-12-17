package apap.tk.SIRekrutmenH9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tk.SIRekrutmenH9.model.PelamarModel;
import apap.tk.SIRekrutmenH9.repository.PelamarDb;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class PelamarServiceImpl implements PelamarService {
    @Autowired
    private PelamarDb pelamarDb;

    @Override
    public void addPelamar(PelamarModel pelamar) {

        pelamarDb.save(pelamar);
    }

    @Override
    public void ubahInformasiPelamar(PelamarModel pelamar, String nama, String noTelepon, String tempatLahir, String alamat, Date tanggalLahir) {
        PelamarModel update = pelamarDb.findById(pelamar.getId()).get();
        update.setNama(nama);
        update.setNoTelepon(noTelepon);
        update.setAlamat(alamat);
        update.setTempatLahir(tempatLahir);
        update.setTanggalLahir(tanggalLahir);
        pelamarDb.save(update);
    }



    @Override
    public PelamarModel getPelamar(Integer idPelamar) {
        if(pelamarDb.findById(idPelamar).isEmpty()){
            return null;
        }else{
            return pelamarDb.findById(idPelamar).get();
        }
    }

}
