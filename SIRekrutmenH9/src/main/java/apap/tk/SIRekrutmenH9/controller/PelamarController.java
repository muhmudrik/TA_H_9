package apap.tk.SIRekrutmenH9.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import apap.tk.SIRekrutmenH9.model.PelamarModel;
import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.service.PelamarService;
import apap.tk.SIRekrutmenH9.service.RoleService;
import apap.tk.SIRekrutmenH9.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping(value = "/pelamar")
@Controller
public class PelamarController {
    @Autowired
    private PelamarService pelamarService;

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private UserService userService;

    @GetMapping(value = "/buat")
    public String getFormBuatPelamar(Model model) {
        return "form-pelamar";
    }

    @PostMapping(value = "/buat/")
    public String addPelamar(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("nama") String nama,
        @RequestParam("no_telepon") String noTelepon,
        @RequestParam("tempat_lahir") String tempatLahir,
        @DateTimeFormat(pattern = "yyyy-MM-dd") 
            @RequestParam("tanggal_lahir") Date tanggalLahir,
        @RequestParam("alamat") String alamat,
        Model model
    ) {
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(roleService.findAll().get(6));
        userService.addUser(user);

        PelamarModel pelamar = new PelamarModel();
        pelamar.setUuidUser(user);
        pelamar.setNama(nama);
        pelamar.setNoTelepon(noTelepon);
        pelamar.setTempatLahir(tempatLahir);
        pelamar.setTanggalLahir(tanggalLahir);
        pelamar.setAlamat(alamat);
        pelamarService.addPelamar(pelamar);

        return "hasil-add-pelamar";
    }
}
