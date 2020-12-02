package apap.tk.SIRekrutmenH9.repository;

import apap.tk.SIRekrutmenH9.model.LowonganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LowonganDB extends JpaRepository<LowonganModel, Integer> {
    Optional<LowonganModel> findById(Integer id);
}