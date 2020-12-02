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

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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
    public String addPelamarForm(Model model) {
        model.addAttribute("msg", "Peringatan: Username yang didaftarkan tidak dapat diganti kembali");
        return "form-pelamar";
    }

    @PostMapping(value = "/buat")
    public String addPelamarSubmit(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("nama") String nama,
        @RequestParam("no_telepon") String noTelepon,
        @RequestParam("tempat_lahir") String tempatLahir,
        @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("tanggal_lahir") Date tanggalLahir,
        @RequestParam("alamat") String alamat,
        Model model
    ) {
        UserModel userBaru = new UserModel();
        PelamarModel pelamar = new PelamarModel();

        userBaru.setUsername(username);
        userBaru.setPassword(password);
        userBaru.setRole(roleService.findAll().get(6));
        userService.addUser(userBaru);

        pelamar.setUuidUser(userBaru);
        pelamar.setNama(nama);
        pelamar.setNoTelepon(noTelepon);
        pelamar.setTempatLahir(tempatLahir);
        pelamar.setTanggalLahir(tanggalLahir);
        pelamar.setAlamat(alamat);
        pelamarService.addPelamar(pelamar);

        model.addAttribute("namapelamar", nama);
        return "add-pelamar";
    }

    @GetMapping(value = "/ubah/{idPelamar}")
    public String updatePelamarForm(
            @PathVariable(required = true) Long idPelamar,
            Model model) {
        if(pelamarService.getPelamar(idPelamar) != null){
            model.addAttribute("pelamar", pelamarService.getPelamar(idPelamar));
            return "form-update-pelamar";
        }
        else{
            return "error-404";
        }
    }

    @RequestMapping(value = "/ubah")
    public String updatePelamarSubmit(
            @ModelAttribute PelamarModel pelamar,
            HttpServletRequest request,
            ModelMap model
    ) {
        pelamarService.ubahInformasiPelamar(pelamar);
        model.clear();
        model.addAttribute("pelamar", pelamar);

        return "hasil-update-pelamar";
    }

}
