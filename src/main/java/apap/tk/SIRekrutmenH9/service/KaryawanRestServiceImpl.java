package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.LamaranModel;
import apap.tk.SIRekrutmenH9.model.PelamarModel;
import apap.tk.SIRekrutmenH9.repository.LamaranDb;
import apap.tk.SIRekrutmenH9.repository.PelamarDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class KaryawanRestServiceImpl implements KaryawanRestService{
    @Autowired
    PelamarDb pelamarDb;

    @Autowired
    LamaranDb lamaranDb;

    @Override
    public List<PelamarModel> getListKaryawanBaru() {
        List<LamaranModel> semuaLamaran = lamaranDb.findAll();
        List<PelamarModel> karyawanBaru = new ArrayList<>();

        for(LamaranModel lamaran : semuaLamaran){
            if(lamaran.getStatus() == 2){
                karyawanBaru.add(pelamarDb.findById(lamaran.getPelamarModel().getId()).get());
            }
        }
        return karyawanBaru;
    }

}
