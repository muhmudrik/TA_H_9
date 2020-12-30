package apap.tk.SIRekrutmenH9.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import apap.tk.SIRekrutmenH9.model.LamaranModel;
import apap.tk.SIRekrutmenH9.model.PelamarModel;
import apap.tk.SIRekrutmenH9.repository.LamaranDb;

@Service
@Transactional
public class LamaranServiceImpl implements LamaranService {

    @Autowired
    private LamaranDb lamaranDb;

    @Override
    public void saveLamaran(LamaranModel lamaran) {
        lamaranDb.save(lamaran);
    }

     @Override
     public List<PelamarModel> getPelamarFromLamaranList (Long id_lowongan) {
         List<LamaranModel> hasilCari = lamaranDb.findByLowonganModel_id_lowongan(id_lowongan);
         List<PelamarModel> listPelamar = new ArrayList<PelamarModel>();

         for (LamaranModel lamaran : hasilCari){
             listPelamar.add(lamaran.getPelamarModel());
         }

         return listPelamar;
     }

    // @Override
    // public List<PelamarModel> getPelamarByLowongan (List<LamaranModel> listLamaran) {
    //     List<PelamarModel> hasilUrai = new ArrayList<PelamarModel>();
    //     for (LamaranModel lamaran : listLamaran) {
    //         hasilUrai.add(lamaran.getPelamarModel());
    //     }
    //     return hasilUrai;
    // }

    @Override
    public List<LamaranModel> getLamaranByLowongan(Long id_lowongan) {
        return lamaranDb.findByLowonganModel_id_lowongan(id_lowongan);
    }

    @Override
    public LamaranModel getLamaranById(Long id_lamaran) {
        return lamaranDb.findById(id_lamaran).get();
    }

    @Override
    public Integer countLamaranDiterima(Long id_lowongan) {
        return lamaranDb.findLamaranDiterimaLowongan(id_lowongan);
    }

    @Override
    public void deleteLamaranById(Long id_lamaran) {
        lamaranDb.deleteById(id_lamaran);

    }

    

    
}
