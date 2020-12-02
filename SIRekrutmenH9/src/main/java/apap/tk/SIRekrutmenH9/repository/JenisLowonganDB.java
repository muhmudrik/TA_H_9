package apap.tk.SIRekrutmenH9.repository;

import apap.tk.SIRekrutmenH9.model.JenisLowonganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JenisLowonganDB extends JpaRepository<JenisLowonganModel, Integer> {
    Optional<JenisLowonganModel> findById(Integer id);

    JenisLowonganModel findByNama(String nama);
}