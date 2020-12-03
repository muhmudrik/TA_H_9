package apap.tk.SIRekrutmenH9.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tk.SIRekrutmenH9.model.LamaranModel;

@Repository
public interface LamaranDb extends JpaRepository<LamaranModel, Long>{
    
}
