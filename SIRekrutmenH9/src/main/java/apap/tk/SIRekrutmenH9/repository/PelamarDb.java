package apap.tk.SIRekrutmenH9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tk.SIRekrutmenH9.model.PelamarModel;

import java.util.Optional;

@Repository
public interface PelamarDb extends JpaRepository<PelamarModel, Integer>{
}
