package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.rest.BaseResponse;
import apap.tk.SIRekrutmenH9.rest.BaseResponse2;
import apap.tk.SIRekrutmenH9.rest.PegawaiData;

public interface PegawaiRestService {
    BaseResponse2 addPegawaiData(PegawaiData pegawai);
    BaseResponse2 getPegawaiData(String username);
}

