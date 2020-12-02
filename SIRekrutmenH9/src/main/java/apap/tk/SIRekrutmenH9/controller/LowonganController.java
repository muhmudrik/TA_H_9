package apap.tk.SIRekrutmenH9.controller;

import apap.tk.SIRekrutmenH9.model.*;

import apap.tk.SIRekrutmenH9.repository.JenisLowonganDB;
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

@Controller
public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

    @Autowired
    private UserService userService;

    @Autowired
    private JenisLowonganDB jenisLowonganDb;

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
    public String ubahLowonganForm(Model model, @PathVariable(value = "id") Integer id) {
        LowonganModel lowongan = lowonganService.getLowonganById(id);
        List<LamaranModel> listLamaran = lowongan.getListLamaran();
        List<JenisLowonganModel> listJenisLowongan = jenisLowonganDb.findAll();

        model.addAttribute("lowongan", lowongan);
        model.addAttribute("listLamaran", listLamaran);
        model.addAttribute("listJenisLowongan", listJenisLowongan);
        return "form-ubah-lowongan";
    }

    @RequestMapping(value = "/lowongan/ubahLowongan/{id}", method = RequestMethod.POST)
    public String ubahLowonganSubmit(
            @PathVariable(value = "id") Integer id,
            @ModelAttribute LowonganModel lowongan,
            Model model
    ) {
        LowonganModel targetLowongan = lowonganService.ubahLowongan(lowongan);
        model.addAttribute("lowongan", targetLowongan);

        return "ubah-lowongan";
    }
}
