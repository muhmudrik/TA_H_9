package apap.tk.SIRekrutmenH9.service;


import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user){
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public boolean passwordMatch(String sandiBaru, String sandiLama) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(sandiBaru, sandiLama);
    }

    @Override
    public boolean passwordChecker(String sandi) {
        // password harus terdiri dari 8 huruf
        boolean min8Huruf = sandi.length() > 7;
        // password harus terdiri dari angka
        boolean adaAngka = sandi.matches(".*[0-9]+.*");
        // password harus terdiri dari huruf
        boolean adaHuruf = sandi.matches(".*[a-zA-Z]+.*");
        return adaHuruf && adaAngka && min8Huruf;
    }


}

