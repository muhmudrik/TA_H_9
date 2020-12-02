package apap.tk.SIRekrutmenH9.repository;

import apap.tk.SIRekrutmenH9.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
//    List<RoleModel> findAll(Long id);
    }


