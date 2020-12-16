package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.JenisLowonganModel;

import java.util.List;

public interface JenisLowonganService {
    List<JenisLowonganModel> getAll();
    JenisLowonganModel getById(Integer id);
}
