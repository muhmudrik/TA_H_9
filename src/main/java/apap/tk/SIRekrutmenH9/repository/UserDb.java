package apap.tk.SIRekrutmenH9.repository;

import apap.tk.SIRekrutmenH9.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDb extends JpaRepository<UserModel, String> {
    UserModel findByUsername(String username);
}
