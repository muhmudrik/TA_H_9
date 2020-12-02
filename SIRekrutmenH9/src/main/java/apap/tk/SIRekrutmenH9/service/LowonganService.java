package apap.tk.SIRekrutmenH9.service;

import apap.tk.SIRekrutmenH9.model.LowonganModel;

import java.util.Optional;

public interface LowonganService {
    Optional<LowonganModel> getLowonganById(Integer id);
}
