package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.LowonganModel;

import java.util.List;

public interface LowonganService {
    void addLowongan(LowonganModel lowongan);

    List<LowonganModel> getLowonganList();

    LowonganModel getLowonganById(Long id);

    LowonganModel ubahLowongan(LowonganModel lowongan);
}