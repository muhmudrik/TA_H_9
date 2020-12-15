package apap.tk.SIRekrutmenH9.controller;

import apap.tk.SIRekrutmenH9.model.*;

import apap.tk.SIRekrutmenH9.repository.JenisLowonganDB;
import apap.tk.SIRekrutmenH9.service.JenisLowonganService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import apap.tk.SIRekrutmenH9.service.LowonganService;
import apap.tk.SIRekrutmenH9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

    @Autowired
    private JenisLowonganService jenisLowonganService;

    @Autowired
    private UserService userService;

    @Autowired
    private JenisLowonganDB jenisLowonganDb;

    @GetMapping("/lowongan/add")
    public String addLowonganFormPage(Model model){
        List<JenisLowonganModel> listJenisLowongan = jenisLowonganService.getAll();

        model.addAttribute("lowongan", new LowonganModel());
        model.addAttribute("listJenisLowongan", listJenisLowongan);

        return "form-add-lowongan";
    }

    @PostMapping("/lowongan/add")
    public String addLowonganSubmit(
            @ModelAttribute LowonganModel lowongan,
            Model model
    ){

        String kodeLowongan = "";
        String posisi = lowongan.getPosisi().substring(0,2).toUpperCase();
        String divisi = lowongan.getDivisi().substring(0,2).toUpperCase();
        String angkaAcak = "";
        String jenisLowonganID = lowongan.getJenisLowongan().getId().toString();
        UserModel userLowongan = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        String angka = "0123456789";
        Random r = new Random();

        for(int i = 0; i < 2; i++){
            angkaAcak += angka.charAt(r.nextInt(10));
        }

        kodeLowongan = divisi + "-" + posisi + "-" + jenisLowonganID + "-" + angkaAcak;

        lowongan.setKodeLowongan(kodeLowongan);
        lowongan.setUser(userLowongan);


        lowonganService.addLowongan(lowongan);
        model.addAttribute("lowongan", lowongan);
        model.addAttribute("idLowongan", lowongan.getId_lowongan());

        return "add-lowongan";
    }

    @RequestMapping("/lowongan/daftarLowongan")
    public String daftarLowongan(Model model){
        List<LowonganModel> listLowongan = lowonganService.getLowonganList();
        UserModel userLogin = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        Long roleUser = userLogin.getRole().getId();
        String uuidStaffRekrutmen = "";

        if (roleUser == 5){
            uuidStaffRekrutmen = userLogin.getId();
        }

        model.addAttribute("listLowongan", listLowongan);
        model.addAttribute("roleUser", roleUser);
        model.addAttribute("uuidStaffRekrutmen", uuidStaffRekrutmen);


        return "daftar-lowongan";
    }
  
    @RequestMapping(value = "/lowongan/ubahLowongan/{id}", method = RequestMethod.GET)
    public String ubahLowonganForm(Model model, @PathVariable(value = "id") Long id) {
        LowonganModel lowongan = lowonganService.getLowonganById(id);
        List<LamaranModel> listLamaran = lowongan.getListLamaran();
        List<JenisLowonganModel> listJenisLowongan = jenisLowonganDb.findAll();

        model.addAttribute("lowongan", lowongan);
        model.addAttribute("listLamaran", listLamaran);
        model.addAttribute("listJenisLowongan", listJenisLowongan);
        return "form-ubah-lowongan";
    }

    @RequestMapping(value = "/lowongan/ubahLowongan", method = RequestMethod.POST)
    public String ubahLowonganSubmit(@ModelAttribute LowonganModel lowongan, Model model) {
        String userId = lowongan.getUser().getId();
        UserModel userModel = userService.getUserById(userId);

        lowongan.setUser(userModel);

        LowonganModel targetLowongan = lowonganService.ubahLowongan(lowongan);
        model.addAttribute("lowongan", targetLowongan);
        return "ubah-lowongan";
    }
}
