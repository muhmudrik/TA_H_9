package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.rest.BaseResponse;
import apap.tk.SIRekrutmenH9.rest.PegawaiData;

public interface PegawaiRestService {
    BaseResponse addPegawaiData(PegawaiData pegawai);
    BaseResponse getPegawaiData(String username);
}

