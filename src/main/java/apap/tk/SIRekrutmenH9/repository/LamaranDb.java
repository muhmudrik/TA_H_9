package apap.tk.SIRekrutmenH9.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apap.tk.SIRekrutmenH9.model.LamaranModel;

@Repository
public interface LamaranDb extends JpaRepository<LamaranModel, Long>{
    
    @Query(value = "SELECT * FROM lamaran WHERE id_lowongan = ':id'", nativeQuery = true)
    List<LamaranModel> findByLowonganModel_id_lowongan(@Param("id") Long id_lowongan);

    @Query(value = "SELECT COUNT(*) FROM lamaran WHERE id_lowongan = ':lowong' AND status = 2", nativeQuery = true)
    Integer findLamaranDiterimaLowongan(@Param("lowong") Long id_lowongan);
}
