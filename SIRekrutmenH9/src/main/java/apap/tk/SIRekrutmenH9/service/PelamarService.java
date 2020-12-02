package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.PelamarModel;

public interface PelamarService {
    void addPelamar(PelamarModel pelamar);
    void ubahInformasiPelamar(PelamarModel pelamar);
    PelamarModel getPelamar(Long idPelamar);
}
