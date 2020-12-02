package apap.tk.SIRekrutmenH9.repository;

<<<<<<< HEAD
public interface UserDb {
}
=======
import apap.tk.SIRekrutmenH9.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}

>>>>>>> 756b9cfbaad20c8775ebf026d582a2248fe2e0d6
