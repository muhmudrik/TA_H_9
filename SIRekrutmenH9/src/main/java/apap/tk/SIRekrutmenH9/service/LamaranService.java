package apap.tk.SIRekrutmenH9.service;

import java.util.List;

import apap.tk.SIRekrutmenH9.model.LamaranModel;
import apap.tk.SIRekrutmenH9.model.PelamarModel;

public interface LamaranService {
    void saveLamaran(LamaranModel lamaran);
    
     List<PelamarModel> getPelamarFromLamaranList(Long id_lowongan);

    // List<PelamarModel> getPelamarByLowongan(List<LamaranModel> listLamaran);

    List<LamaranModel> getLamaranByLowongan(Long id_lowongan);

    LamaranModel getLamaranById(Long id_lamaran);

    Integer countLamaranDiterima(Long id_lowongan);
    
}
