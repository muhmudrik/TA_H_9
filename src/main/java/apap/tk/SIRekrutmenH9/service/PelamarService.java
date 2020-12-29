package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.PelamarModel;

import java.util.Date;

public interface PelamarService {
    void addPelamar(PelamarModel pelamar);
    void ubahInformasiPelamar(PelamarModel pelamar, String username, String noTelepon, String tempatLahir, String alamat, Date tanggalLahir);
    PelamarModel getPelamar(Integer idPelamar);
}
