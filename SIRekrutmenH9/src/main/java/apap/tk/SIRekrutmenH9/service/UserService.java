package apap.tk.SIRekrutmenH9.service;

<<<<<<< HEAD
public interface UserService {
}
=======
import apap.tk.SIRekrutmenH9.model.UserModel;
import org.apache.catalina.User;

public interface UserService {
    UserModel addUser (UserModel user);
    String encrypt (String password);
    UserModel getUserByUsername(String username);
    boolean passwordMatch(String sandiBaru, String sandiLama);
    boolean passwordChecker(String sandi);

}
>>>>>>> 756b9cfbaad20c8775ebf026d582a2248fe2e0d6
