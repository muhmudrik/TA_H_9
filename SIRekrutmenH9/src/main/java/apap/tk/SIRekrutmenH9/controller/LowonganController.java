package apap.tk.SIRekrutmenH9.controller;

import apap.tk.SIRekrutmenH9.model.LowonganModel;
import apap.tk.SIRekrutmenH9.model.UserModel;
import apap.tk.SIRekrutmenH9.service.LowonganService;
import apap.tk.SIRekrutmenH9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LowonganController {
    @Autowired
    private LowonganService lowonganService;

    @Autowired
    private UserService userService;

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
}
