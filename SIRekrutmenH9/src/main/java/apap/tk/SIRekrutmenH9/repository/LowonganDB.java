package apap.tk.SIRekrutmenH9.repository;

<<<<<<< HEAD
import org.springframework.stereotype.Repository;

@Repository
public interface LowonganDB {

=======
import apap.tk.SIRekrutmenH9.model.LowonganModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LowonganDB extends JpaRepository<LowonganModel, Integer> {
    Optional<LowonganModel> findById(Integer id);
>>>>>>> 5eabf3f09a9ec3c31612b5dc1e2ba2c10c7044df
}